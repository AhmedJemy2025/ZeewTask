
package com.app.core.di

import android.app.Application
import android.content.Context
import com.app.core.di.modules.ContextModule
import com.app.core.di.modules.NetworkModule
import com.app.core.networking.api.AuthAPI
import com.app.core.networking.repositiories.AuthRepository
import dagger.Component
import javax.inject.Singleton

/**
 * Core component that all module's components depend on.
 *
 * @see Component
 */
@Singleton
@Component(
    modules = [
        ContextModule::class,
        NetworkModule::class
    ]
)
interface CoreComponent {


    fun context(): Context


    fun authService(): AuthAPI


    fun authRepository(): AuthRepository


}

fun provideCoreComponent(application: Application): CoreComponent {
    return DaggerCoreComponent
        .builder()
        .contextModule(ContextModule(application = application))
        .build()
}
