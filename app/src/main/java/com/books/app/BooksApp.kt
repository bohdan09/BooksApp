package com.books.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module

class BooksApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(koinModules())
        }
    }

    private fun koinModules(): List<Module> = com.books.app.di.koinModules
}