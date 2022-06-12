package com.loogika.mikroisp.domain.core.viewstate

import com.loogika.mikroisp.network.di.model.error.ErrorModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

suspend inline infix fun <T> Flow<Uitate<T>>.collectAsSuccess(
    crossinline action: suspend (value: T) -> Unit
): Flow<Uitate<T>> {
    collect {
        if (it is Uitate.Success) {
            action(it.data)
        }
    }
    return this
}

suspend inline infix fun <T> Flow<Uitate<T>>.collectAsFailureErrorModel(
    crossinline action: suspend (value: ErrorModel) -> Unit
): Flow<Uitate<T>> {
    collect {
        if (it is Uitate.FailureErrorModel) {
            action(it.errorMessage)
        }
    }
    return this
}

suspend inline infix fun <T> Flow<Uitate<T>>.collectAsFailure(
    crossinline action: suspend (value: Uitate.UnExpectedError) -> Unit
): Flow<Uitate<T>> {
    collect {
        if (it is Uitate.Failure<*>) {
            action(Uitate.UnExpectedError)
        }
    }
    return this
}

suspend inline infix fun <T> Flow<Uitate<T>>.collectErrorCodeException(
    crossinline action: suspend (value: Any) -> Unit
): Flow<Uitate<T>> {
    collect {
        if (it is Uitate.FailureCodeException<*>) {
            action(it)
        }
    }
    return this
}

suspend inline infix fun <T> Flow<Uitate<T>>.collectAsUnAuthorized(
    crossinline action: suspend (value: Uitate.UnAuthorized) -> Unit
): Flow<Uitate<T>> {
    collect {
        if (it is Uitate.UnAuthorized) {
            action(Uitate.UnAuthorized)
        }
    }
    return this
}

suspend inline infix fun <T> Flow<Uitate<T>>.collectAsErrorConnection(
    crossinline action: suspend (value: Uitate.ConnectionError) -> Unit
): Flow<Uitate<T>> {
    collect {
        if (it is Uitate.ConnectionError) {
            action(it)
        }
    }
    return this
}