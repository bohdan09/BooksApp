package com.books.app.domain.usecase

import com.books.app.domain.model.Book
import com.books.app.domain.model.BookAndGenre
import com.books.app.domain.repository.GetBooksRepository
import kotlinx.coroutines.flow.Flow

class GetBooksDataUseCase(
    private val getBooksDataRepository: GetBooksRepository,
) : GetBooksUseCase {

    override fun getBooks(): Flow<List<BookAndGenre>> {
        return getBooksDataRepository.getBooks()
    }

    override fun getBooksByGenre(genre: String): Flow<List<Book>> {
        return getBooksDataRepository.getBooksByGenre(genre)
    }

    override fun getBookByBookId(bookId: String): Flow<Book> {
        return getBooksDataRepository.getBookByBookId(bookId)
    }

    override fun getRecommendationBooks(): Flow<List<Book>> {
        return getBooksDataRepository.getRecommendationBooks()
    }
}