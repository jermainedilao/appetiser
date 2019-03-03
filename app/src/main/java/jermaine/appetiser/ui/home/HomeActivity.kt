package jermaine.appetiser.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import jermaine.appdomain.App
import jermaine.appetiser.R
import jermaine.appetiser.di.components.DaggerHomeComponent
import jermaine.appetiser.di.components.HomeComponent
import jermaine.appetiser.ui.detail.DetailsActivity
import jermaine.appetiser.ui.home.adapter.FeedAdapter
import jermaine.appetiser.ui.home.adapter.callbacks.FeedClickListener
import jermaine.appetiser.utils.EXTRA_FEED_JSON
import jermaine.domain.feed.model.Feed
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    companion object {
        const val TAG = "HomeActivity"
    }

    @Inject
    lateinit var viewModelFactory: HomeViewModel.Factory

    var homeComponent: HomeComponent? = null

    private lateinit var viewModel: HomeViewModel
    private lateinit var compositeDisposable: CompositeDisposable
    private lateinit var adapter: FeedAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        homeComponent = DaggerHomeComponent.builder()
            .appComponent((application as App).appComponent)
            .applicationContext(applicationContext)
            .build()
        homeComponent!!.inject(this)

        compositeDisposable = CompositeDisposable()
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)

        initializeRecyclerView()
        fetchFeed()
    }

    private fun initializeRecyclerView() {
        adapter = FeedAdapter()
        adapter.setClickListener(object : FeedClickListener {
            override fun onClick(feed: Feed) {
                startActivity(
                    Intent(this@HomeActivity, DetailsActivity::class.java)
                        .apply {
                            putExtra(EXTRA_FEED_JSON, Gson().toJson(feed))
                        }
                )
            }

        })
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = this@HomeActivity.adapter
        }

        swipeRefreshLayout.setOnRefreshListener {
            fetchFeed(true)
        }

        // Live data
        viewModel.list.observe(this, Observer {
            adapter.updateData(it)
        })
    }

    private val refreshStateListener: (Boolean) -> Unit = { value ->
        if (value && swipeRefreshLayout.isRefreshing) {
            Log.d(TAG, "fetchFeed: already refreshing")
        } else {
            swipeRefreshLayout.isRefreshing = value
        }
    }

    private fun fetchFeed(forceFetch: Boolean = false) {
        viewModel.fetchFeed(refreshStateListener, forceFetch)
    }

    override fun onDestroy() {
        viewModel.storeLastVisit(System.currentTimeMillis())
        compositeDisposable.clear()
        homeComponent = null
        super.onDestroy()
    }
}
