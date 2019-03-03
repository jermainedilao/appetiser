package jermaine.data.feed.db

import android.util.Log
import io.reactivex.Completable
import io.reactivex.Observable
import jermaine.data.feed.db.model.FeedDataModelDao
import jermaine.data.util.toDataModel
import jermaine.data.util.toDomainModel
import jermaine.domain.feed.model.Feed


class FeedDaoImpl(private val feedDataModelDao: FeedDataModelDao) : FeedDao {
    companion object {
        const val TAG = "FeedDaoImpl"
    }

    /**
     * Fetches feed from local.
     */
    override fun fetchFeed(): Observable<List<Feed>> {
        Log.d(TAG, "fetchFeedLocal")
        return feedDataModelDao.fetchFeedList()
            .toObservable()
            .doOnNext { Log.d(TAG, "fetchFeedLocal: ${it.size}") }
            .flatMap { feedList ->
                Observable.just(feedList.map { it.toDomainModel() })  // Transform to domain model.
            }
    }

    /**
     * Saves feed to local.
     */
    override fun saveFeedList(value: List<Feed>): Completable {
        Log.d(TAG, "saveFeedList")
        feedDataModelDao.saveFeedList(
            value.map { it.toDataModel() }
        )
        return Completable.complete()  // Manual completable. Room's completable is not working.
    }
}