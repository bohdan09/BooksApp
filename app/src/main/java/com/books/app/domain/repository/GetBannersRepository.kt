package com.books.app.domain.repository

import com.books.app.domain.model.TopBannerSlide
import kotlinx.coroutines.flow.Flow

interface GetBannersRepository {

    fun getBanners(): Flow<List<TopBannerSlide>>
}