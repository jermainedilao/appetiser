package jermaine.appdomain.di.modules

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import jermaine.appdomain.APP_PREFERENCES
import jermaine.appdomain.App
import javax.inject.Singleton


/**
 * Module that contains objects to provide for the whole application lifetime.
 */
@Module
class AppModule {
    @Provides
    @Singleton
    fun providesSharedPreferences(app: App): SharedPreferences =
        app.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
}