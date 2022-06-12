package com.loogika.mikroisp.domain.core.viewstate

import com.loogika.mikroisp.network.di.model.error.ErrorModel


sealed class Uitate<out T> where T : Any? {
    object Loading : Uitate<Nothing>()
    object Unknown : Uitate<Nothing>()
    object UnAuthorized : Uitate<Nothing>()
    object UnExpectedError : Uitate<Nothing>()
    object EmptySuccess : Uitate<Nothing>()
    object ConnectionError : Uitate<Nothing>()

    data class Success<T>(
        val data: T
    ) : Uitate<T>()

    data class Failure<T>(
        val errorMessage: T
    ) : Uitate<Nothing>()

    data class FailureCodeException<T>(
        val exception: T
    ) : Uitate<Nothing>()

    data class FailureErrorModel(
        val errorMessage: ErrorModel
    ) : Uitate<Nothing>()
}

