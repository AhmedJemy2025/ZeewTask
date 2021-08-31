package com.app.core.di.modules

import com.app.core.networking.api.AuthAPI
import com.app.core.networking.repositiories.AuthRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }


    @Singleton
    @Provides
    fun provideHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.addInterceptor(interceptor)
        return clientBuilder.build()
    }


    @Singleton
    @Provides
    fun provideRetrofitBuilder() = Retrofit.Builder()
        .baseUrl("https://store.zeew.eu")
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    @Singleton
    @Provides
    fun provideAuthService(retrofit: Retrofit) = retrofit.create(AuthAPI::class.java)


    @Singleton
    @Provides
    fun provideAuthRepository(service: AuthAPI) = AuthRepository(service)


}