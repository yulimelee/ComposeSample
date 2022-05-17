package com.example.composesample.data

import androidx.annotation.DrawableRes
import com.example.composesample.R

sealed class NavigationItem(val route: String, @DrawableRes val icon: Int, var title: String) {
    object Home: NavigationItem("home", R.drawable.ic_home, "Home")
    object Trending: NavigationItem("trending", R.drawable.ic_trending, "Trending")
}