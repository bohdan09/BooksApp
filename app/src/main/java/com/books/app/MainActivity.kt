package com.books.app

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalAnimationApi
import com.books.app.presentation.navigation.appNavigation
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT,
                Color.TRANSPARENT
            ),
            navigationBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT,
                Color.TRANSPARENT
            )
        )
        super.onCreate(savedInstanceState)
        initRemoteConfig()
        appNavigation()
    }

    private fun initRemoteConfig() {
        val firebaseRemoteConfig: FirebaseRemoteConfig by inject<FirebaseRemoteConfig>()
        firebaseRemoteConfig.fetchAndActivate()
            .addOnCompleteListener(this) { task ->
                if (!task.isSuccessful) {
                    Toast.makeText(
                        this, "Remote config fetch failed",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}