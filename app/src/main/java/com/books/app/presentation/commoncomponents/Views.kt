package com.books.app.presentation.commoncomponents

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun VerticalSpacer(width: Dp) {
    Spacer(
        modifier = Modifier
            .width(width)
    )
}

@Composable
fun HorizontalSpacer(height: Dp) {
    Spacer(
        modifier = Modifier
            .height(height)
    )
}