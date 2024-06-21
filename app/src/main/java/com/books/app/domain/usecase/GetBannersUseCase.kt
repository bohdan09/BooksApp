package com.books.app.domain.usecase

import com.books.app.domain.model.Book
import kotlinx.coroutines.flow.Flow

interface GetBannersUseCase {

    fun getBanners(): Flow<List<Book>>
}