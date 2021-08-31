
package com.app.zeewtask.di

import android.content.Context
import com.app.zeewtask.MyApp
import dagger.Module
import dagger.Provides

/**
 * Class that contributes to the object graph [AppComponent].
 *
 * @see Module
 */
@Module
class AppModule {

    @Provides
    fun provideContext(application: MyApp): Context = application.applicationContext
}
