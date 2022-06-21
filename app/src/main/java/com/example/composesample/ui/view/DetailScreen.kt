package com.example.composesample.ui.view

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.composesample.BASE_THUMBNAIL_URL
import com.example.composesample.data.Movie
import com.example.composesample.ui.view.common.CustomAppBar
import com.example.composesample.ui.viewmodel.DetailViewModel


@Composable
fun DetailView(activity: Activity, viewModel: DetailViewModel) {
    Scaffold(
        topBar = {
            CustomAppBar(
                title = viewModel.movieData.value?.title ?: "",
                showBackBtn = true,
                onClickBackBtn = {
                    activity.onBackPressed()
                }
            )
        },
    ) {
        DetailScreen(movie = viewModel.movieData.value)
    }
}


@Composable
fun DetailScreen(movie: Movie?) {
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp)
        ) {
            AsyncImage(
                model = "$BASE_THUMBNAIL_URL${movie?.posterPath?.replace("/", "")}",
                contentDescription = movie?.originalTitle
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.padding(10.dp, 10.dp, 0.dp, 0.dp)
        ) {
            movie?.releaseDate?.let { Text(text = "개봉일 : $it") }
        }
    }
}