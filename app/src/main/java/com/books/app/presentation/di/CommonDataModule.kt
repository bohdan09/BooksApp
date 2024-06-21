package com.books.app.presentation.di

import com.books.app.data.repository.GetBannersDataRepository
import com.books.app.data.repository.GetBooksDataRepository
import com.books.app.data.repository.RemoteConfigDataRepository
import com.books.app.data.repository.RemoteConfigRepository
import com.books.app.domain.repository.GetBannersRepository
import com.books.app.domain.repository.GetBooksRepository
import com.books.app.domain.usecase.GetBannersDataUseCase
import com.books.app.domain.usecase.GetBannersUseCase
import com.books.app.domain.usecase.GetBooksDataUseCase
import com.books.app.domain.usecase.GetBooksUseCase
import org.koin.dsl.module

internal val commonDataModule = module {
    factory<GetBooksRepository> {
        GetBooksDataRepository(
            firebaseRemoteConfig = get(),
            gson = get()
        )
    }

    factory<RemoteConfigRepository> {
        RemoteConfigDataRepository(
            firebaseRemoteConfig = get()
        )
    }

    factory<GetBannersRepository> {
        GetBannersDataRepository(
            firebaseRemoteConfig = get(),
            gson = get()
        )
    }

    factory<GetBooksUseCase> {
        GetBooksDataUseCase(getBooksDataRepository = get())
    }

    factory<GetBannersUseCase> {
        GetBannersDataUseCase(
            getBannersRepository = get()
        )
    }
}