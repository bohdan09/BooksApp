package com.books.app.presentation.di

import com.books.app.data.repository.GetBooksDataRepository
import com.books.app.domain.repository.GetBooksRepository
import com.books.app.domain.usecase.GetBooksDataUseCase
import com.books.app.domain.usecase.GetBooksUseCase
import org.koin.dsl.module

internal val commonDataModule = module {

    factory<GetBooksUseCase> {
        GetBooksDataUseCase(getBooksDataRepository = get())
    }

    factory<GetBooksRepository> {
        GetBooksDataRepository(
            gson = get()
        )
    }
}