package com.example.composesample.framework.repository

import com.example.composesample.data.MovieResponse
import com.example.composesample.framework.BaseApiResponse
import com.example.composesample.framework.MovieApiHelper
import com.example.composesample.framework.NetworkResult
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ViewModelScoped
class MovieRepository @Inject constructor(
    private val movieApiHelper: MovieApiHelper
) : BaseApiResponse() {
    suspend fun getDiscoverMovie(apiKey: String, page: Int): Flow<NetworkResult<MovieResponse>> {
        return flow {
            emit(safeApiCall { movieApiHelper.getDiscoverMovies(apiKey, page) })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTrendingMovie(apiKey: String, page: Int): Flow<NetworkResult<MovieResponse>> {
        return flow {
            emit(safeApiCall { movieApiHelper.getTrendingMovies(apiKey, page) })
        }.flowOn(Dispatchers.IO)
    }
}