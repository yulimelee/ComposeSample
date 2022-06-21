package com.example.composesample.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.composesample.data.Movie
import com.example.composesample.framework.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository
): ViewModel() {
    companion object{
        private val TAG = DetailViewModel::class.java.simpleName
    }

    val movieData = MutableLiveData<Movie>()


}