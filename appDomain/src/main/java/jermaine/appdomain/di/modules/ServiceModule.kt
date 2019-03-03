package jermaine.appdomain.di.modules

import dagger.Module
import dagger.Provides
import jermaine.data.ApiService
import jermaine.data.feed.service.FeedService
import jermaine.data.feed.service.FeedServiceImpl


@Module
class ServiceModule {
    @Provides
    fun providesFeedService(apiService: ApiService): FeedService = FeedServiceImpl(apiService)
}