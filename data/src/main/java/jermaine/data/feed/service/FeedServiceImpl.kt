package jermaine.data.feed.service

import android.util.Log
import io.reactivex.Observable
import jermaine.data.ApiService
import jermaine.domain.feed.model.Feed


class FeedServiceImpl(private val apiService: ApiService) : FeedService {
    companion object {
        const val TAG = "FeedServiceImpl"
    }
    
    override fun fetchFeed(): Observable<List<Feed>> {
        Log.d(TAG, "fetchFeed: ")
        return apiService
            .fetchFeed("star", "au", "movie")
            .map { it.results }
    }
}