package com.books.app.presentation.navigation.graphs.onboarding

import com.books.app.presentation.navigation.NavFlow
import com.books.app.presentation.navigation.NavFlowRoute

sealed class SplashFlow(
    override val flowRoute: NavFlowRoute,
    override val label: String,
    override val argKey1: String? = null,
    override val argKey2: String? = null,
    override val argKey3: String? = null,
) : NavFlow() {

    object SplashScreen : SplashFlow(NavFlowRoute("SplashScreen"), "SplashScreen")
}
