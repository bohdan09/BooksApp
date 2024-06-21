package com.books.app.data.repository

import com.books.app.domain.model.Book
import com.books.app.domain.model.BookAndGenre
import com.books.app.domain.model.Books
import com.books.app.domain.repository.GetBooksRepository
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class GetBooksDataRepository(
    private val firebaseRemoteConfig: FirebaseRemoteConfig,
    private val gson: Gson,
) : GetBooksRepository {

    override fun getBooks(): Flow<List<BookAndGenre>> {
        val json = firebaseRemoteConfig.getString("json_data")
        val book = gson.fromJson(json, Books::class.java)
        val genres = book.books.map { it.genre }
        val booksAndGenres = mutableSetOf<BookAndGenre>()

        genres.forEach { genre ->
            val books = book.books.filter { it.genre == genre }
            val bookAndGenre = BookAndGenre(
                genre = genre,
                books = books
            )
            booksAndGenres.add(bookAndGenre)
        }
        return flowOf(booksAndGenres.toList())
    }

    override fun getBooksByGenre(genre: String): Flow<List<Book>> {
        val json = firebaseRemoteConfig.getString("json_data")
        val book = gson.fromJson(json, Books::class.java)
        val genres = book.books.map { it.genre }
        val booksAndGenres = mutableSetOf<BookAndGenre>()

        genres.forEach { genreType ->
            val books = book.books.filter { it.genre == genreType }
            val bookAndGenre = BookAndGenre(
                genre = genreType,
                books = books
            )
            booksAndGenres.add(bookAndGenre)
        }
//        Log.d("kasdfmnkdm", (booksAndGenres.find { it.genre == genre }?.books ?: emptyList()).size.toString())

        return flowOf(booksAndGenres.find { it.genre == genre }?.books ?: emptyList())
    }
}