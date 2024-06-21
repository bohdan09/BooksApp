package com.books.app.presentation.navigation

@JvmInline
value class NavFlowRoute(val route: String)

fun String.toFlowRoute() = NavFlowRoute(this)
