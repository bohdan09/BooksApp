package com.books.app.data.repository

import com.books.app.domain.model.Book
import com.books.app.domain.model.BookAndGenre
import com.books.app.domain.model.BooksInfo
import com.books.app.domain.repository.GetBooksRepository
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class GetBooksDataRepository(
    firebaseRemoteConfig: FirebaseRemoteConfig,
    gson: Gson,
) : GetBooksRepository {

    private val json = firebaseRemoteConfig.getString("json_data")
    private val booksInfo = gson.fromJson(json, BooksInfo::class.java)

    override fun getBooks(): Flow<List<BookAndGenre>> {
        val genres = booksInfo.books.map { it.genre }
        val booksAndGenres = mutableSetOf<BookAndGenre>()

        genres.forEach { genre ->
            val books = booksInfo.books.filter { it.genre == genre }
            val bookAndGenre = BookAndGenre(
                genre = genre,
                books = books
            )
            booksAndGenres.add(bookAndGenre)
        }
        return flowOf(booksAndGenres.toList())
    }

    override fun getBooksByGenre(genre: String): Flow<List<Book>> {
        val genres = booksInfo.books.map { it.genre }
        val booksAndGenres = mutableSetOf<BookAndGenre>()

        genres.forEach { genreType ->
            val books = booksInfo.books.filter { it.genre == genreType }
            val bookAndGenre = BookAndGenre(
                genre = genreType,
                books = books
            )
            booksAndGenres.add(bookAndGenre)
        }

        return flowOf(booksAndGenres.find { it.genre == genre }?.books ?: emptyList())
    }

    override fun getBookByBookId(bookId: String): Flow<Book> {
        val book =
            booksInfo.books.find { book: Book -> book.id.toString() == bookId } ?: Book.default()
        return flowOf(book)
    }

    override fun getRecommendationBooks(): Flow<List<Book>> {
        val recommendationBooks = mutableListOf<Book>()
        booksInfo.you_will_like_section.forEach { id ->
            recommendationBooks.add(booksInfo.books.find { book: Book -> book.id == id }
                ?: Book.default())
        }
        return flowOf(recommendationBooks)
    }
}