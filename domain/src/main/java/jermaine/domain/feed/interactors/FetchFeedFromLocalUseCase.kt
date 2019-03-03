package jermaine.domain.feed.interactors

import io.reactivex.Observable
import jermaine.domain.feed.FeedRepository
import jermaine.domain.feed.model.Feed
import jermaine.domain.interactorTypes.ObservableUseCase


/**
 * Use case responsible in fetching feed from local.
 **/
class FetchFeedFromLocalUseCase(private val feedRepository: FeedRepository) : ObservableUseCase<List<Feed>> {
    override fun execute(): Observable<List<Feed>> = feedRepository.fetchFeedFromLocal()
}