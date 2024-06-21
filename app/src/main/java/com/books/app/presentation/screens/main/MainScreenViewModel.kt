package com.books.app.presentation.screens.main

import com.books.app.domain.model.BookAndGenre
import com.books.app.domain.model.TopBannerSlide
import com.books.app.domain.usecase.GetBannersUseCase
import com.books.app.domain.usecase.GetBooksUseCase
import com.books.app.presentation.base.BaseViewModel
import com.books.app.presentation.utils.coroutines.CoroutineContextProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest

class MainScreenViewModel(
    coroutineContextProvider: CoroutineContextProvider,
    private val getBooksUseCase: GetBooksUseCase,
    private val getBannersUseCase: GetBannersUseCase,
) : BaseViewModel(coroutineContextProvider) {

    private val _booksState: MutableStateFlow<List<BookAndGenre>> = MutableStateFlow(emptyList())
    val booksState = _booksState.asStateFlow()

    private val _bannersState: MutableStateFlow<List<TopBannerSlide>> =
        MutableStateFlow(emptyList())
    val bannersState = _bannersState.asStateFlow()

    init {
        getBooks()
        getBanners()
    }

    private fun getBooks() {
        launch(ioContext) {
            getBooksUseCase.getBooks().collectLatest {
                _booksState.emit(it)
            }
        }
    }

    private fun getBanners() {
        launch(ioContext) {
            getBannersUseCase.getBanners().collectLatest {
                bannersState.value.ifEmpty {
                    _bannersState.emit(it)
                }
            }
        }
    }
}