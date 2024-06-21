package com.books.app.domain.usecase

import com.books.app.domain.model.TopBannerSlide
import kotlinx.coroutines.flow.Flow

interface GetBannersUseCase {

    fun getBanners(): Flow<List<TopBannerSlide>>
}