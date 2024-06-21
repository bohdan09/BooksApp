package com.books.app.presentation.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.books.app.presentation.utils.TAG
import com.books.app.presentation.utils.coroutines.CoroutineContextProvider
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

open class BaseViewModel(coroutineContextProvider: CoroutineContextProvider) : ViewModel() {

    protected val ioContext: CoroutineContext = coroutineContextProvider.io
    protected val mainContext: CoroutineContext = coroutineContextProvider.main

    init {
        viewModelScope.coroutineContext + CoroutineExceptionHandler(
            handler = { context, exception ->
                Log.e(
                    this@BaseViewModel.TAG,
                    "CoroutineExceptionHandler handled exception in ${this@BaseViewModel}. Exception: ${exception.stackTraceToString()}"
                )
            }
        )
    }

    fun launch(
        context: CoroutineContext = EmptyCoroutineContext,
        block: suspend CoroutineScope.() -> Unit,
    ) = viewModelScope.launch(context, block = block)
}
