package com.example.composesample.framework

import com.example.composesample.data.MovieResponse
import retrofit2.Response
import javax.inject.Inject

class MovieHelperImpl @Inject constructor(private val movieApiService: MovieApiService) :
    MovieApiHelper {
    override suspend fun getDiscoverMovies(apiKey: String, page: Int): Response<MovieResponse> =
        movieApiService.getDiscoverMovies(apiKey, page)

    override suspend fun getTrendingMovies(apiKey: String, page: Int): Response<MovieResponse> =
        movieApiService.getTrendingMovies(apiKey, page)
}