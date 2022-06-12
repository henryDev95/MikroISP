package com.loogika.mikroisp.network.di

import android.content.Context
import com.loogika.mikroisp.network.BuildConfig.API_HOST
import com.loogika.mikroisp.network.BuildConfig.API_KEY
import com.loogika.mikroisp.network.R
import com.loogika.mikroisp.network.di.interceptor.AuthInterceptor
import com.loogika.mikroisp.network.di.webservice.WebServiceApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideOkHttpClient(@ApplicationContext appContext: Context
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(API_KEY))
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100,TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(@ApplicationContext appContext: Context, okHttpClient: OkHttpClient):Retrofit{
        return Retrofit.Builder()
            .baseUrl(API_HOST)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

   @Singleton
    @Provides
    fun provideMovieApiClient(retrofit: Retrofit): WebServiceApi {
        return retrofit.create(WebServiceApi::class.java)
    }
}