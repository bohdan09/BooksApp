package com.books.app.presentation.commoncomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import kotlinx.coroutines.Dispatchers

@Composable
fun CacheAsyncImage(
    modifier: Modifier,
    imageUrl: String,
    contentScale: ContentScale = ContentScale.Crop,
) {
    val context = LocalContext.current
    val imageRequest = ImageRequest.Builder(context)
        .data(imageUrl)
        .dispatcher(Dispatchers.IO)
        .memoryCacheKey(imageUrl)
        .diskCacheKey(imageUrl)
        .diskCachePolicy(CachePolicy.ENABLED)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .build()

    AsyncImage(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(brush = ShimmerBrush(targetValue = 1300f)),
        contentScale = contentScale,
        model = imageRequest,

        contentDescription = null
    )
}