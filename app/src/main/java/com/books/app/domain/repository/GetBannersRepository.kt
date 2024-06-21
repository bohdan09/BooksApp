package com.books.app.domain.repository

import com.books.app.domain.model.Book
import kotlinx.coroutines.flow.Flow

interface GetBannersRepository {

    fun getBanners(): Flow<List<Book>>
}