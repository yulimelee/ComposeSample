package com.example.composesample.ui.view.common

import androidx.compose.foundation.background
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.composesample.data.NavigationItem

@Composable
fun BottomNavBar(navController: NavController) {
    val navItems = listOf(
        NavigationItem.Home,
        NavigationItem.Trending
    )
    BottomNavigation(Modifier.background(Color.White), contentColor = Color.Yellow) {
        navItems.forEach { _navItem ->
            BottomNavigationItem(
                selected = false,
                onClick = {
                    navController.navigate(_navItem.route) {
                        navController.graph.startDestinationRoute?.let { _route ->
                            popUpTo(_route) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }

                },
                icon = {
                    Icon(
                        painter = painterResource(id = _navItem.icon),
                        contentDescription = "home"
                    )
                },
                label = { Text(text = _navItem.title) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.4F),
                alwaysShowLabel = true
            )
        }
    }
}
