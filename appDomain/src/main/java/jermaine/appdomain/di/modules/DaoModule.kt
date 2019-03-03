package jermaine.appdomain.di.modules

import dagger.Module
import dagger.Provides
import jermaine.data.db.AppDatabase
import jermaine.data.feed.db.FeedDao
import jermaine.data.feed.db.FeedDaoImpl


@Module
class DaoModule {
    @Provides
    fun providesFeedDao(appDatabase: AppDatabase): FeedDao = FeedDaoImpl(appDatabase.feedDao())
}