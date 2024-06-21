package com.books.app.presentation.navigation.graphs

import com.books.app.presentation.navigation.NavFlow
import com.books.app.presentation.navigation.NavFlowRoute

sealed class GraphsFlow(
    override val flowRoute: NavFlowRoute,
    override val label: String,
) : NavFlow() {

    object OnboardingGraph : GraphsFlow(NavFlowRoute("OnboardingGraph"), "OnboardingGraph")
    object MainGraph : GraphsFlow(NavFlowRoute("MainGraph"), "MainGraph")
}