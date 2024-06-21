package com.books.app.domain.repository

import com.books.app.domain.model.Book
import com.books.app.domain.model.BookAndGenre
import kotlinx.coroutines.flow.Flow

interface GetBooksRepository {

    fun getBooks(): Flow<List<BookAndGenre>>

    fun getBooksByGenre(genre: String): Flow<List<Book>>
}