package com.books.app.data.repository

import com.books.app.domain.model.BooksInfo
import com.books.app.domain.model.TopBannerSlide
import com.books.app.domain.repository.GetBannersRepository
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class GetBannersDataRepository(
    private val firebaseRemoteConfig: FirebaseRemoteConfig,
    private val gson: Gson,
) : GetBannersRepository {

    override fun getBanners(): Flow<List<TopBannerSlide>> {
        val json = firebaseRemoteConfig.getString("json_data")
        val banner = gson.fromJson(json, BooksInfo::class.java)
        return flowOf(banner.top_banner_slides)

    }
}