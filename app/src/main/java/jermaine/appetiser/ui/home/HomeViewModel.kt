package jermaine.appetiser.ui.home

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import jermaine.appetiser.utils.*
import jermaine.domain.feed.interactors.FetchFeedFromLocalUseCase
import jermaine.domain.feed.interactors.FetchFeedFromServerUseCase
import jermaine.domain.feed.model.Feed
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class HomeViewModel(
    private val fetchFeedFromServerUseCase: FetchFeedFromServerUseCase,
    private val fetchFeedFromLocalUseCase: FetchFeedFromLocalUseCase,
    private val sharedPreferences: SharedPreferences,
    private val applicationContext: Context
) : ViewModel() {

    companion object {
        const val TAG = "HomeViewModel"
    }

    class Factory @Inject constructor(
        private val fetchFeedFromServerUseCase: FetchFeedFromServerUseCase,
        private val fetchFeedFromLocalUseCase: FetchFeedFromLocalUseCase,
        private val sharedPreferences: SharedPreferences,
        private val applicationContext: Context
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return HomeViewModel(
                fetchFeedFromServerUseCase,
                fetchFeedFromLocalUseCase,
                sharedPreferences,
                applicationContext
            ) as T
        }
    }

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    var list: MutableLiveData<List<Feed>> = MutableLiveData()

    /**
     * Fetches feed both from local and from server.
     *
     * If server response is received within 1000ms, it skips displaying items from local.
     *
     * @param refreshState emits boolean value for refresh state.
     * @param forceFetch set to true to ignore interval and forceFetch fetch from server.
     */
    fun fetchFeed(refreshState: (Boolean) -> Unit, forceFetch: Boolean = false) {
        val local = fetchFeedFromLocalUseCase.execute()
        val server = fetchFeedFromServerUseCase.execute()
            .doOnSubscribe {
                refreshState.invoke(true)
            }
            .doAfterNext {
                refreshState.invoke(false)  // Response from server is received.
            }
            .doAfterNext {
                storeLastFetchTimestamp(System.currentTimeMillis())
            }

        val fetch = when {
            shouldFetch() && applicationContext.hasNetworkConnection() -> {
                // Last fetch timestamp exceeds interval. Fetching both local and server.
                Observable.concat(local, server)
            }
            forceFetch && applicationContext.hasNetworkConnection() -> {
                // Forced. Fetch from server.
                server
            }
            forceFetch -> {
                // Fetch local only. This can happen when there's no internet connection or hasn't exceeded interval.
                refreshState.invoke(false)
                local
            }
            else -> {
                if (list.value?.isNotEmpty() == true) {
                    // When fetchFeed is called and list is not empty. Do nothing.
                    // This can happen when device is rotated.
                    return
                } else {
                    // By default fetches local only.
                    local
                }
            }
        }

        val disposable = fetch
            .subscribeOn(Schedulers.io())
            .debounce(1000, TimeUnit.MILLISECONDS)
            .map { list ->
                if (getLastVisit() != 0L) {
                    // Add last visit header.
                    list.toMutableList()
                        .apply {
                            add(0, Feed(artistName = HEADER, trackId = getLastVisit()))
                        }
                } else {
                    list
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                list.value = it
            }, {
                it.printStackTrace()
                throw RuntimeException(it)
            })
        compositeDisposable.add(disposable)
    }

    /**
     * Returns true if last fetch timestamp exceeds the interval.
     *
     * @see PREF_FETCH_INTERVAL
     */
    private fun shouldFetch(): Boolean {
        return System.currentTimeMillis() - sharedPreferences.getLong(
            PREF_LAST_FETCH_TIMESTAMP,
            0
        ) > PREF_FETCH_INTERVAL
    }

    private fun storeLastFetchTimestamp(value: Long) {
        sharedPreferences.edit { putLong(PREF_LAST_FETCH_TIMESTAMP, value) }
    }

    fun storeLastVisit(currentTimeMillis: Long) {
        sharedPreferences.edit { putLong(PREF_LAST_VISIT, currentTimeMillis) }
    }

    /**
     * Returns last visit timestamp.
     */
    private fun getLastVisit() = sharedPreferences.getLong(PREF_LAST_VISIT, 0)

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}