package com.books.app.di

import com.books.app.presentation.utils.coroutines.CoroutineContextProvider
import com.books.app.presentation.utils.coroutines.DefaultCoroutineContextProvider
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.google.gson.Gson
import org.koin.dsl.module

val appModule = module {

    single<FirebaseRemoteConfig> {
        val remoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3600
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig
    }
    single<CoroutineContextProvider> {
        DefaultCoroutineContextProvider
    }

    single<Gson> {
        Gson()
    }
}
