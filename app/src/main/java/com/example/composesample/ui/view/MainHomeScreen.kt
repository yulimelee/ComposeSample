package com.example.composesample.ui.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.composesample.BASE_THUMBNAIL_URL
import com.example.composesample.data.Movie
import com.example.composesample.data.NavigationItem
import com.example.composesample.ui.view.common.BottomNavBar
import com.example.composesample.ui.view.common.CustomAppBar
import com.example.composesample.ui.viewmodel.MainViewModel
import kotlinx.coroutines.flow.Flow

interface OnClickItem{
    fun onClick(data: Movie)
}


@Composable
fun MainHomeScreen(viewModel: MainViewModel, onClickItem: OnClickItem) {
    val title = viewModel.title.observeAsState()
    val navController = rememberNavController()

    Scaffold(
        Modifier.background(Color.White),
        topBar = { CustomAppBar(title = title.value ?: "", false) },
        bottomBar = { BottomNavBar(navController) },
    ) {
        Box(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, it.calculateBottomPadding())) {
            Navigation(navController, viewModel, onClickItem)
        }
    }
}

@Composable
fun Navigation(navController: NavHostController, viewModel: MainViewModel, onClickItem: OnClickItem) {
    NavHost(navController, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Home.route) {
            HomeView(viewModel.discoverMovies, onClickItem)
            viewModel.changeTopTitle(NavigationItem.Home.title)
        }
        composable(NavigationItem.Trending.route) {
            TrendingView(viewModel.trendingMovies, onClickItem)
            viewModel.changeTopTitle(NavigationItem.Trending.title)
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeView(movies: Flow<PagingData<Movie>>, onClickItem: OnClickItem) {
    val lazyMovieItems: LazyPagingItems<Movie> = movies.collectAsLazyPagingItems()
    val minSize = LocalConfiguration.current.screenWidthDp.div(2)
    LazyVerticalGrid(cells = GridCells.Adaptive(
        minSize.dp
    ), content = {
        items(lazyMovieItems.itemCount) { index ->
            MovieItem(movie = lazyMovieItems[index], onClickItem)
        }
    })
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TrendingView(movies: Flow<PagingData<Movie>>, onClickItem: OnClickItem) {
    val lazyMovieItems: LazyPagingItems<Movie> = movies.collectAsLazyPagingItems()
    val minSize = LocalConfiguration.current.screenWidthDp.div(2)
    LazyVerticalGrid(cells = GridCells.Adaptive(
        minSize.dp
    ), content = {
        items(lazyMovieItems.itemCount) { index ->
            MovieItem(movie = lazyMovieItems[index], onClickItem)
        }
    })
}

@Composable
fun MovieItem(movie: Movie?, onClickItem: OnClickItem) {
    Column(
        Modifier
            .padding(20.dp)
            .clickable {
                if (movie != null) {
                    onClickItem.onClick(movie)
                }
            }) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            AsyncImage(
                model = "$BASE_THUMBNAIL_URL${movie?.posterPath?.replace("/", "")}",
                contentDescription = movie?.originalTitle ?: ""
            )
        }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(text = movie?.originalTitle ?: "", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
    }
}
