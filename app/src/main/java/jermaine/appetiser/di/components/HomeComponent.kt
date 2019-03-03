package jermaine.appetiser.di.components

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import jermaine.appdomain.di.components.AppComponent
import jermaine.appdomain.di.modules.*
import jermaine.appetiser.di.modules.ActivityModule
import jermaine.appetiser.di.scopes.ActivityScope
import jermaine.appetiser.ui.home.HomeActivity


@ActivityScope
@Component(
    modules = [
        ActivityModule::class,
        DaoModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        ServiceModule::class,
        UseCaseModule::class
    ],
    dependencies = [AppComponent::class]
)
interface HomeComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicationContext(applicationContext: Context): Builder
        fun appComponent(appComponent: AppComponent): Builder
        fun build(): HomeComponent
    }

    fun inject(homeActivity: HomeActivity)
}