package com.example.composesample

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.composesample.data.Movie
import com.example.composesample.ui.view.DetailView
import com.example.composesample.ui.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity: AppCompatActivity() {

    private val viewModel by viewModels<DetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.movieData.value = intent?.getSerializableExtra("movie") as? Movie
        setContent {
            DetailView(this@DetailActivity, viewModel)
        }
    }
}