package com.books.app.presentation.navigation.graphs.main

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.books.app.presentation.navigation.FeatureNavigator
import com.books.app.presentation.navigation.NavFlow
import com.books.app.presentation.navigation.util.firstKeyValue
import com.books.app.presentation.navigation.util.routeWithKeys
import com.books.app.presentation.navigation.util.secondKeyValue
import com.books.app.presentation.screens.details.DetailsScreen
import com.books.app.presentation.screens.main.MainScreen

@ExperimentalAnimationApi
fun NavGraphBuilder.mainGraph(route: NavFlow, appNavigator: FeatureNavigator) =
    navigation(
        route = route.flowRoute.route,
        startDestination = MainFlow.MainScreen.flowRoute.route
    ) {
        composable(
            MainFlow.MainScreen.flowRoute.route
        ) {
            MainScreen { genre, bookId ->
                appNavigator.navigateToFlowWithArgs(
                    MainFlow.DetailsScreen,
                    navOptions = null,
                    MainFlow.DetailsScreen.argKey1!! to bookId.ifEmpty { "default" },
                    MainFlow.DetailsScreen.argKey2!! to genre.ifEmpty { "default" }
                )
            }
        }

        composable(
            MainFlow.DetailsScreen.routeWithKeys().route,
            arguments = listOf(
                navArgument(
                    MainFlow.DetailsScreen.argKey1 ?: ""
                ) {
                    type = NavType.StringType
                },
                navArgument(
                    MainFlow.DetailsScreen.argKey2 ?: ""
                ) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val bookId = backStackEntry.firstKeyValue(MainFlow.DetailsScreen) ?: ""
            val genre = backStackEntry.secondKeyValue(MainFlow.DetailsScreen) ?: ""
            DetailsScreen(
                bookId = bookId,
                genre = genre
            ) {
                appNavigator.navigateUp()
            }
        }
    }
