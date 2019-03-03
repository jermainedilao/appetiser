package jermaine.appdomain.di.components

import android.content.SharedPreferences
import dagger.BindsInstance
import dagger.Component
import jermaine.appdomain.App
import jermaine.appdomain.di.modules.AppModule
import jermaine.appdomain.di.modules.DbModule
import jermaine.data.db.AppDatabase
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        DbModule::class
    ]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun app(app: App): Builder
        fun build(): AppComponent
    }

    // Provide to dependent components
    fun appDatabase(): AppDatabase

    fun sharedPreferences(): SharedPreferences
}