package com.books.app.data.repository

import com.books.app.domain.model.Book
import com.books.app.domain.model.banner.Banner
import com.books.app.domain.repository.GetBannersRepository
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class GetBannersDataRepository(
    private val firebaseRemoteConfig: FirebaseRemoteConfig,
    private val gson: Gson,
) : GetBannersRepository {

    override fun getBanners(): Flow<List<Book>> {
        val json = firebaseRemoteConfig.getString("json_data")
        val banner = gson.fromJson(json, Banner::class.java)
        return flowOf(banner.books)

    }
}