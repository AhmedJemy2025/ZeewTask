package com.app.zeewtask.di

import com.app.core.di.CoreComponent
import com.app.core.di.scopes.AppScope
import com.app.zeewtask.MyApp

import dagger.Component

/**
 * App component that application component's components depend on.
 *
 * @see Component
 */
@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class]
)
interface AppComponent {

    /**
     * Inject dependencies on application.
     *
     * @param application The sample application.
     */
    fun inject(application: MyApp)
}
