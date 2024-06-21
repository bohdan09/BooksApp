package com.books.app.domain.model

data class Book(
    val author: String,
    val cover_url: String,
    val genre: String,
    val id: Int,
    val likes: String,
    val name: String,
    val quotes: String,
    val summary: String,
    val views: String,
) {

    companion object {
        fun default() = Book(
            author = "",
            cover_url = "",
            genre = "",
            id = 0,
            likes = "",
            name = "",
            quotes = "",
            summary = "",
            views = ""
        )
    }
}