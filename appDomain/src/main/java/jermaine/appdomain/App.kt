package jermaine.appdomain

import android.app.Application
import jermaine.appdomain.di.components.AppComponent
import jermaine.appdomain.di.components.DaggerAppComponent


class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .app(this)
            .build()
    }
}