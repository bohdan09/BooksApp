package com.books.app.presentation.navigation

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.books.app.presentation.navigation.graphs.GraphsFlow
import com.books.app.presentation.navigation.graphs.main.mainGraph
import com.books.app.presentation.navigation.graphs.onboarding.splashGraph
import com.books.app.ui.theme.BooksAppTheme

@ExperimentalAnimationApi
fun ComponentActivity.appNavigation() = setContent {
    WindowCompat.setDecorFitsSystemWindows(window, false)
    BooksAppTheme {
        NavigationAppNavigation()
    }
}

@ExperimentalAnimationApi
@Composable
fun NavigationAppNavigation() {
    val navController = rememberNavController()
    val appNavigator: FeatureNavigator = remember(navController) {
        FeatureNavigator(navController)
    }
    Scaffold(
        contentWindowInsets = WindowInsets(0.dp),
        contentColor = Color.Transparent
    ) { _ ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
        ) {
            NavHost(
                navController = navController,
                startDestination = GraphsFlow.OnboardingGraph.flowRoute.route
            ) {
                splashGraph(GraphsFlow.OnboardingGraph, appNavigator)
                mainGraph(GraphsFlow.MainGraph, appNavigator)
            }
        }
    }
}
