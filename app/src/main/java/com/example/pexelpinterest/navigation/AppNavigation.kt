package com.example.pexelpinterest.navigation

import android.net.http.SslCertificate.saveState
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.pexelpinterest.R
import com.example.pexelpinterest.ui.features.bookmarks.BookmarkScreen
import com.example.pexelpinterest.ui.features.details.DetailsScreen
import com.example.pexelpinterest.ui.features.list.PhotoListScreen
import com.squareup.moshi.internal.Util
import kotlinx.coroutines.selects.select
import kotlinx.serialization.Serializable

@Serializable
data object ListScreenRoute

@Serializable
data object BookmarkScreenRoute

@Serializable
data class DetailsScreenRoute(val photoId: Long)

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val isDetailsScreen = currentDestination?.hasRoute<DetailsScreenRoute>() == true


    Scaffold(
        bottomBar = {

            BottomBar(navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = ListScreenRoute,
            modifier = Modifier.padding(
                top = if (isDetailsScreen) 0.dp else innerPadding.calculateTopPadding(),
                bottom = if (isDetailsScreen) 0.dp else innerPadding.calculateBottomPadding()
            )
        ) {
            composable<ListScreenRoute> {
                PhotoListScreen(onPhotoClick = { id ->
                    navController.navigate(DetailsScreenRoute(id))
                })
            }
            composable<BookmarkScreenRoute> {
                BookmarkScreen(onPhotoClick = { id ->
                    navController.navigate(DetailsScreenRoute(id))
                })
            }
            composable<DetailsScreenRoute> {
                DetailsScreen(
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}

@Composable
fun BottomBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val showBottomBar = currentDestination?.hasRoute<DetailsScreenRoute>() == false

    if (showBottomBar) {
        NavigationBar {
            NavigationBarItem(
                selected = currentDestination.hasRoute<ListScreenRoute>(),
                onClick = {
                    navController.navigate(ListScreenRoute) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    val isSelected = currentDestination.hasRoute<ListScreenRoute>()
                    Icon(
                        painter = painterResource(
                            id =
                                if (isSelected) R.drawable.ic_home_red else R.drawable.ic_home
                        ), "List"
                    )
                }
            )

            NavigationBarItem(
                selected = currentDestination.hasRoute<BookmarkScreenRoute>(),
                onClick = {
                    navController.navigate(BookmarkScreenRoute) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    val isSelected = currentDestination.hasRoute<BookmarkScreenRoute>()
                    Icon(
                        painter = painterResource(
                            id =
                                if (isSelected) R.drawable.ic_bookmark_red else R.drawable.ic_bookmark
                        ), "Bookmarks"
                    )
                }
            )
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun BottomBarPreview() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomBar(navController) }
    ) { innerPadding ->
        Box(Modifier.padding(innerPadding))
    }

}