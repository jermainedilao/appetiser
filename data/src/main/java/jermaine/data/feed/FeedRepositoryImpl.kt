package jermaine.data.feed

import io.reactivex.Completable
import io.reactivex.Observable
import jermaine.data.feed.db.FeedDao
import jermaine.data.feed.service.FeedService
import jermaine.domain.feed.FeedRepository
import jermaine.domain.feed.model.Feed


class FeedRepositoryImpl(
    private val feedService: FeedService,
    private val feedDao: FeedDao
) : FeedRepository {
    /**
     * Returns feed coming from server.
     *
     * Also saves items received from server to local.
     **/
    override fun fetchFeedFromServer(): Observable<List<Feed>> = feedService.fetchFeed()
        .doOnNext { saveFeedToLocal(it) }

    /**
     * Returns feed from local.
     */
    override fun fetchFeedFromLocal(): Observable<List<Feed>> = feedDao.fetchFeed()

    /**
     * Saves feed to local.
     */
    override fun saveFeedToLocal(value: List<Feed>): Completable = feedDao.saveFeedList(value)
}