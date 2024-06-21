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
    private val _booksState: MutableStateFlow<List<Book>> = MutableStateFlow(emptyList())
    val booksState = _booksState.asStateFlow()

    init {
        getBooksByGenre(genre)
    }

    private fun getBooksByGenre(genre: String) {
        launch(ioContext) {
            getBooksUseCase.getBooksByGenre(genre).collectLatest {
                _booksState.emit(it)
            }
        }
    }
}