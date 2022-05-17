package com.example.composesample.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.composesample.data.Movie
import com.example.composesample.data.MovieSource
import com.example.composesample.framework.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {
    companion object {
        private val TAG = MainViewModel::class.java.simpleName
    }

    val title = MutableLiveData("")

    val movies: Flow<PagingData<Movie>> = Pager(PagingConfig(pageSize = 20)) {
        MovieSource(movieRepository)
    }.flow.cachedIn(viewModelScope)

    fun changeTopTitle(titleString: String) {
        title.postValue(titleString)
    }
}