package jermaine.appdomain.di.modules

import dagger.Module
import dagger.Provides
import jermaine.domain.feed.FeedRepository
import jermaine.domain.feed.interactors.FetchFeedFromLocalUseCase
import jermaine.domain.feed.interactors.FetchFeedFromServerUseCase


@Module
class UseCaseModule {
    @Provides
    fun providesFetchFeedFromServerUseCase(feedRepository: FeedRepository): FetchFeedFromServerUseCase =
        FetchFeedFromServerUseCase(feedRepository)

    @Provides
    fun providesFetchFeedFromLocalUseCase(feedRepository: FeedRepository): FetchFeedFromLocalUseCase =
        FetchFeedFromLocalUseCase(feedRepository)
}