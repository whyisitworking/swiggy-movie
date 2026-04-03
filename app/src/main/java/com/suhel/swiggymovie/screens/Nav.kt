package com.suhel.swiggymovie.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.suhel.swiggymovie.screens.details.DetailsScreen
import com.suhel.swiggymovie.screens.search.SearchScreen

@Composable
fun AppNav() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "search"
    ) {
        composable("search") {
            SearchScreen(onNavToDetails = { imdbId ->
                navController.navigate("details/$imdbId")
            })
        }

        composable(
            route = "details/{imdbId}",
            arguments = listOf(
                navArgument("imdbId") {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) {
            DetailsScreen(onBack = { navController.navigateUp() })
        }
    }
}
