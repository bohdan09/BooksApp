package com.books.app.domain.model

data class BookAndGenre(
    val genre: String,
    val books: List<Book>,
) {

    companion object {
        fun default() = BookAndGenre(
            genre = "",
            books = emptyList()
        )
    }
}
