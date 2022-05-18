package com.example.composesample.framework

import com.example.composesample.data.MovieResponse
import retrofit2.Response

interface MovieApiHelper {
    suspend fun getDiscoverMovies(apiKey: String, page: Int): Response<MovieResponse>
    suspend fun getTrendingMovies(apiKey: String, page: Int): Response<MovieResponse>
}