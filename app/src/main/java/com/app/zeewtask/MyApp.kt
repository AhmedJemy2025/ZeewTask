
package com.app.zeewtask

import android.app.Application
import android.content.Context
import com.app.core.di.CoreComponent
import com.app.core.di.DaggerCoreComponent
import com.app.core.di.modules.ContextModule
import com.app.zeewtask.di.DaggerAppComponent

class MyApp : Application() {

    lateinit var coreComponent: CoreComponent


    companion object {

        @JvmStatic
        fun coreComponent(context: Context) = (context.applicationContext as? MyApp)?.coreComponent
    }


    override fun onCreate() {
        super.onCreate()
        initCoreDependencyInjection()
        initAppDependencyInjection()
    }


    private fun initAppDependencyInjection() {
        DaggerAppComponent
            .builder()
            .coreComponent(coreComponent)
            .build()
            .inject(this)
    }

    private fun initCoreDependencyInjection() {
        coreComponent = DaggerCoreComponent
            .builder()
            .contextModule(ContextModule(this))
            .build()
    }

}


