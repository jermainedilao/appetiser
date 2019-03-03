package jermaine.data.feed.db

import io.reactivex.Completable
import io.reactivex.Observable
import jermaine.domain.feed.model.Feed


interface FeedDao {
    fun fetchFeed(): Observable<List<Feed>>

    fun saveFeedList(value: List<Feed>): Completable
}