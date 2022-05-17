package com.example.composesample.framework

import com.example.composesample.TRENDING_SUB_URL
import com.example.composesample.data.TrendingResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET(TRENDING_SUB_URL)
    suspend fun getDiscoverMovies(@Query("api_key") apiKey: String) : Response<TrendingResponse>
}