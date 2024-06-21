package com.books.app.data.repository

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class RemoteConfigDataRepository(
    private val firebaseRemoteConfig: FirebaseRemoteConfig,
) : RemoteConfigRepository {
    override fun getSmth(): Flow<String> {
        val a = firebaseRemoteConfig.getString("test_value")
        return flowOf(a)
    }
}

interface RemoteConfigRepository {
    fun getSmth(): Flow<String>
}