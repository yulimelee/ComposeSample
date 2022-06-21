package com.example.composesample

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.composesample.data.Movie
import com.example.composesample.ui.view.MainHomeScreen
import com.example.composesample.ui.view.Navigation
import com.example.composesample.ui.view.OnClickItem
import com.example.composesample.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainHomeScreen(viewModel = viewModel, onClickItem = object : OnClickItem{
                override fun onClick(data: Movie) {
                    startActivity(Intent(this@MainActivity, DetailActivity::class.java).apply {
                        putExtra("movie" , data)
                    })
                }
            })
        }
    }
}
