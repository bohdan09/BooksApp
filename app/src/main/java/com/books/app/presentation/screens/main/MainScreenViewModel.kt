package com.books.app.presentation.screens.main

import com.books.app.domain.model.BookAndGenre
import com.books.app.domain.usecase.GetBooksUseCase
import com.books.app.presentation.base.BaseViewModel
import com.books.app.presentation.utils.coroutines.CoroutineContextProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest

class MainScreenViewModel(
    coroutineContextProvider: CoroutineContextProvider,
    private val getBooksUseCase: GetBooksUseCase,
) : BaseViewModel(coroutineContextProvider) {

    private val _booksState: MutableStateFlow<List<BookAndGenre>> = MutableStateFlow(emptyList())
    val booksState = _booksState.asStateFlow()

    init {
        getBooks()
    }

    private fun getBooks() {
        launch(ioContext) {
            getBooksUseCase.getBooks().collectLatest {
                _booksState.emit(it)
            }
        }
    }
}