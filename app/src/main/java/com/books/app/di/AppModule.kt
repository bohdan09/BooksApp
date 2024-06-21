package com.books.app.di

import com.books.app.presentation.utils.coroutines.CoroutineContextProvider
import com.books.app.presentation.utils.coroutines.DefaultCoroutineContextProvider
import com.google.gson.Gson
import org.koin.dsl.module

val appModule = module {
    single<CoroutineContextProvider> {
        DefaultCoroutineContextProvider
    }

    single<Gson> {
        Gson()
    }
}
