package com.books.app.presentation.navigation.graphs.onboarding

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.books.app.presentation.navigation.FeatureNavigator
import com.books.app.presentation.navigation.NavFlow
import com.books.app.presentation.navigation.graphs.main.MainFlow
import com.books.app.presentation.screens.splash.SplashScreen

@ExperimentalAnimationApi
fun NavGraphBuilder.splashGraph(route: NavFlow, appNavigator: FeatureNavigator) =
    navigation(
        route = route.flowRoute.route,
        startDestination = SplashFlow.SplashScreen.flowRoute.route
    ) {
        composable(
            SplashFlow.SplashScreen.flowRoute.route
        ) {
            SplashScreen(
                goToMainScreen = {
                    appNavigator.navigateToFlow(
                        MainFlow.MainScreen,
                        navOptions = NavOptions.Builder()
                            .setPopUpTo(0, true)
                            .build()
                    )
                }
            )
        }
    }
