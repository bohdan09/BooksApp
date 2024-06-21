package com.books.app.presentation.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.books.app.R
import com.books.app.presentation.commoncomponents.HorizontalSpacer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

@Composable
fun SplashScreen(
    goToMainScreen: () -> Unit,
) = CompositionLocalProvider(
    androidx.lifecycle.compose.LocalLifecycleOwner provides androidx.compose.ui.platform.LocalLifecycleOwner.current,
) {
    LaunchedEffect(key1 = Unit) {
        delay(3000)
        withContext(Dispatchers.Main) {
            goToMainScreen()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.background),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.hearts_background),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Book App",
                style = TextStyle(
                    fontSize = 58.sp,
                    lineHeight = 52.sp,
                    fontFamily = FontFamily(Font(R.font.georgia, weight = FontWeight.Bold)),
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFDD48A1)
                )
            )
            Text(
                text = "Welcome to Book App",
                style = TextStyle(
                    fontSize = 24.sp,
                    lineHeight = 26.4.sp,
                    fontFamily = FontFamily(Font(R.font.nunito_sans, weight = FontWeight.Normal)),
                    fontWeight = FontWeight.Normal,
                    color = Color(0xCCFFFFFF)
                )
            )

            HorizontalSpacer(height = 44.dp)

            LinearProgressIndicator(
                modifier = Modifier
                    .width(270.dp)
                    .clip(RoundedCornerShape(6.dp)),
                color = Color(0xFFFFFFFF),
                trackColor = Color(0x33FFFFFF)
            )
        }
    }
}