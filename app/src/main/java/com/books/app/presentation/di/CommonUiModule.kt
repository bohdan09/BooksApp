package com.books.app.presentation.di

import com.books.app.presentation.screens.main.MainScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

internal val commonUiModule = module {
    viewModelOf(::MainScreenViewModel)
}