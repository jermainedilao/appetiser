package jermaine.domain.feed

import io.reactivex.Completable
import io.reactivex.Observable
import jermaine.domain.feed.model.Feed


/**
 * All actions related to feed should be declared here.
 */
interface FeedRepository {
    fun fetchFeedFromServer(): Observable<List<Feed>>

    fun fetchFeedFromLocal(): Observable<List<Feed>>

    fun saveFeedToLocal(value: List<Feed>): Completable
}