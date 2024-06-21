package com.books.app.domain.usecase

import com.books.app.domain.model.TopBannerSlide
import com.books.app.domain.repository.GetBannersRepository
import kotlinx.coroutines.flow.Flow

class GetBannersDataUseCase(
    private val getBannersRepository: GetBannersRepository,
) : GetBannersUseCase {

    override fun getBanners(): Flow<List<TopBannerSlide>> {
        return getBannersRepository.getBanners()
    }
}