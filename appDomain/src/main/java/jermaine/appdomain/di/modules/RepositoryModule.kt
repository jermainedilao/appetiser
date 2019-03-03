package jermaine.appdomain.di.modules

import dagger.Module
import dagger.Provides
import jermaine.data.feed.FeedRepositoryImpl
import jermaine.data.feed.db.FeedDao
import jermaine.data.feed.service.FeedService
import jermaine.domain.feed.FeedRepository

@Module
class RepositoryModule {
    @Provides
    fun providesFeedRepository(feedService: FeedService, feedDao: FeedDao): FeedRepository =
        FeedRepositoryImpl(feedService, feedDao)
}