package com.books.app.domain.usecase

import com.books.app.domain.model.Book
import com.books.app.domain.model.BookAndGenre
import kotlinx.coroutines.flow.Flow

interface GetBooksUseCase {

    fun getBooks(): Flow<List<BookAndGenre>>

    fun getBooksByGenre(genre: String): Flow<List<Book>>
}