package com.books.app.presentation.navigation.graphs.main

import com.books.app.presentation.navigation.NavFlow
import com.books.app.presentation.navigation.NavFlowRoute

sealed class MainFlow(
    override val flowRoute: NavFlowRoute,
    override val label: String,
    override val argKey1: String? = null,
    override val argKey2: String? = null,
    override val argKey3: String? = null,
) : NavFlow() {

    object MainScreen : MainFlow(NavFlowRoute("MainScreen"), "MainScreen")

    object DetailsScreen :
        MainFlow(NavFlowRoute("DetailsScreen"), "DetailsScreen", "bookId", "genre")
}
