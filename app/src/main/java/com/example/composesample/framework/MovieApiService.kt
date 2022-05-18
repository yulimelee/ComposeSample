package com.example.composesample.framework

import com.example.composesample.DISCOVER_SUB_URL
import com.example.composesample.TRENDING_SUB_URL
import com.example.composesample.data.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET(DISCOVER_SUB_URL)
    suspend fun getDiscoverMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<MovieResponse>

    @GET(TRENDING_SUB_URL)
    suspend fun getTrendingMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<MovieResponse>
}