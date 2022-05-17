package com.example.composesample.ui.viewmodel

import androidx.lifecycle.*
import com.example.composesample.data.Movie
import com.example.composesample.data.TrendingResponse
import com.example.composesample.framework.NetworkResult
import com.example.composesample.framework.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel(){
    companion object{
        private val TAG = MainViewModel::class.java.simpleName
    }

    val title = MutableLiveData("")
    private val discoverResponse = MutableLiveData<TrendingResponse?>()
    val discoverMovieList = Transformations.switchMap(discoverResponse){
        liveData {
            val list = mutableListOf<Movie>()
            it?.results?.forEach {
                list.add(it)
            }
            emit(list)
        }
    }

    fun changeTopTitle(titleString: String){
        title.postValue(titleString)
    }



    fun getTrendingMovies(apiKey: String){
        viewModelScope.launch {
            movieRepository.getDiscoverMovie(apiKey).collectLatest { _networkResult ->
                if (_networkResult is NetworkResult.Success){
                    discoverResponse.postValue(_networkResult.data)
                }
            }
        }
    }
}