package com.books.app.domain.usecase

import com.books.app.domain.model.Book
import com.books.app.domain.repository.GetBannersRepository
import kotlinx.coroutines.flow.Flow

class GetBannersDataUseCase(
    private val getBannersRepository: GetBannersRepository,
) : GetBannersUseCase {

    override fun getBanners(): Flow<List<Book>> {
        return getBannersRepository.getBanners()
    }
}