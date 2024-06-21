package com.books.app.presentation.screens.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun DetailsScreen(
    bookId: String,
    genre: String,
    onBackClick: () -> Unit,
) = CompositionLocalProvider(
    androidx.lifecycle.compose.LocalLifecycleOwner provides androidx.compose.ui.platform.LocalLifecycleOwner.current,
) {

}