package com.example.composesample.framework

import com.example.composesample.data.TrendingResponse
import retrofit2.Response
import javax.inject.Inject

class MovieHelperImpl @Inject constructor(private val movieApiService: MovieApiService) : MovieApiHelper {
    override suspend fun getDiscoverMovies(apiKey: String): Response<TrendingResponse> =
        movieApiService.getDiscoverMovies(apiKey)
}