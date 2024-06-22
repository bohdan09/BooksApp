package com.books.app.presentation.screens.details

import com.books.app.domain.model.Book
import com.books.app.domain.usecase.GetBooksUseCase
import com.books.app.presentation.base.BaseViewModel
import com.books.app.presentation.utils.coroutines.CoroutineContextProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest

class DetailsScreenViewModel(
    coroutineContextProvider: CoroutineContextProvider,
    private val getBooksUseCase: GetBooksUseCase,
    private val bookId: String,
    private val genre: String,
) : BaseViewModel(coroutineContextProvider) {

    private val _booksByGenreState: MutableStateFlow<List<Book>> = MutableStateFlow(emptyList())
    val booksByGenreState = _booksByGenreState.asStateFlow()

    private val _recommendationBooksState: MutableStateFlow<List<Book>> =
        MutableStateFlow(emptyList())
    val recommendationBooksState = _recommendationBooksState.asStateFlow()

    private val _selectedBookState: MutableStateFlow<Book> = MutableStateFlow(Book.default())
    val selectedBookState = _selectedBookState.asStateFlow()

    init {
        getRecommendationBooksState()
        if (genre == "default") {
            getBookById(bookId)
        } else getBooksByGenre(genre)
    }


    fun setSelectedBook(bookId: String) {
        launch(ioContext) {
            getBooksUseCase.getBookByBookId(bookId).collectLatest { book: Book ->
                _selectedBookState.emit(book)
            }
        }
    }

    private fun getBookById(bookId: String) {
        launch(ioContext) {
            getBooksUseCase.getBookByBookId(bookId).collectLatest { book: Book ->
                getBooksByGenre(genre = book.genre)
                _selectedBookState.emit(book)
            }
        }
    }

    private fun getBooksByGenre(genre: String) {
        launch(ioContext) {
            getBooksUseCase.getBooksByGenre(genre).collectLatest {
                _booksByGenreState.emit(it)
                setSelectedBook(bookId = bookId)
            }
        }
    }

    private fun getRecommendationBooksState() {
        launch(ioContext) {
            getBooksUseCase.getRecommendationBooks().collectLatest {
                _recommendationBooksState.emit(it)
            }
        }
    }
}