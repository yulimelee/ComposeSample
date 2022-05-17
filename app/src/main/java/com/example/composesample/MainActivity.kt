package com.example.composesample

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.composesample.ui.view.MainHomeScreen
import com.example.composesample.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainHomeScreen(viewModel = viewModel)
        }
        viewModel.getTrendingMovies(resources.getString(R.string.movie_api_key))
    }
}
