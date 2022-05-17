package com.example.composesample.framework.module

import com.example.composesample.framework.MovieApiHelper
import com.example.composesample.framework.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Singleton
    @Provides
    fun provideRepository(movieApiHelper: MovieApiHelper) = MovieRepository(movieApiHelper)
}