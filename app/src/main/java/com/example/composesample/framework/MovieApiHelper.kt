package com.example.composesample.framework

import com.example.composesample.data.TrendingResponse
import retrofit2.Response

interface MovieApiHelper {
    suspend fun getDiscoverMovies(apiKey: String, page: Int): Response<TrendingResponse>
}