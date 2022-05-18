package com.example.composesample.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.composesample.framework.repository.MovieRepository
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class TrendingMovieSource @Inject constructor(
    private val movieRepository: MovieRepository,
    private val apiKey: String = "4caa0f2c011a1a817105be229620f377"
): PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val nextPage = params.key ?: 1
            val movieListResponse = movieRepository.getTrendingMovie(apiKey, nextPage).firstOrNull()

            LoadResult.Page(
                data = movieListResponse?.data?.results ?: emptyList(),
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = movieListResponse?.data?.page?.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}