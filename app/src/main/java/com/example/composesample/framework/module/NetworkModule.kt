package com.example.composesample.framework.module

import com.example.composesample.BASE_API_URL
import com.example.composesample.BuildConfig
import com.example.composesample.framework.MovieApiHelper
import com.example.composesample.framework.MovieApiService
import com.example.composesample.framework.MovieHelperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_API_URL)
        .client(okHttpClient)
        .build()


    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG){
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }else{
        OkHttpClient
            .Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit) = retrofit.create(MovieApiService::class.java)

    @Singleton
    @Provides
    fun provideApiHelper(movieHelperImpl: MovieHelperImpl): MovieApiHelper = movieHelperImpl
}