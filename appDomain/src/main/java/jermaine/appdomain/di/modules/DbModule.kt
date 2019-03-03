package jermaine.appdomain.di.modules

import androidx.room.Room
import dagger.Module
import dagger.Provides
import jermaine.appdomain.App
import jermaine.data.db.AppDatabase
import javax.inject.Singleton

@Module
class DbModule {
    @Singleton
    @Provides
    fun providesAppDatabase(app: App): AppDatabase =
        Room.databaseBuilder(app, AppDatabase::class.java, "app_database").build()
}