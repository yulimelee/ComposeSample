package com.example.composesample.ui.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.composesample.BASE_API_URL
import com.example.composesample.BASE_THUMBNAIL_URL
import com.example.composesample.data.Movie
import com.example.composesample.data.NavigationItem
import com.example.composesample.ui.view.common.BottomNavBar
import com.example.composesample.ui.view.common.CustomAppBar
import com.example.composesample.ui.viewmodel.MainViewModel


@Composable
fun MainHomeScreen(viewModel: MainViewModel) {
    val title = viewModel.title.observeAsState()
    val navController = rememberNavController()

    Scaffold(
        Modifier.background(Color.White),
        topBar = { CustomAppBar(title = title.value ?: "") },
        bottomBar = { BottomNavBar(navController) },
    ) {
        Box(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, it.calculateBottomPadding())) {
            Navigation(navController, viewModel)
        }
    }
}

@Composable
fun Navigation(navController: NavHostController, viewModel: MainViewModel) {
    NavHost(navController, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Home.route) {
            HomeView(viewModel)
            viewModel.changeTopTitle(NavigationItem.Home.title)
        }
        composable(NavigationItem.Trending.route) {
            TrendingView(viewModel)
            viewModel.changeTopTitle(NavigationItem.Trending.title)
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeView(viewModel: MainViewModel) {
    val trendingList = viewModel.discoverMovieList.observeAsState()
    val minSize = LocalConfiguration.current.screenWidthDp.div(2)

    LazyVerticalGrid(cells = GridCells.Adaptive(
        minSize.dp
    ), content = {
        items(trendingList.value?.size ?: 0){ index ->
            DiscoverItem(movie = trendingList.value?.get(index))
        }
    })
}

@Composable
fun TrendingView(viewModel: MainViewModel) {

}

@Composable
fun DiscoverItem(movie: Movie?) {
    if (movie == null) return

    Column(Modifier.padding(20.dp)) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            AsyncImage(model = "$BASE_THUMBNAIL_URL${movie.posterPath?.replace("/", "")}", contentDescription = movie.originalTitle)
        }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(text = movie.originalTitle, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
    }
}
