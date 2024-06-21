package com.books.app.di

import com.books.app.presentation.di.commonModule

val koinModules = mutableListOf(appModule).apply {
    addAll(commonModule)
}
