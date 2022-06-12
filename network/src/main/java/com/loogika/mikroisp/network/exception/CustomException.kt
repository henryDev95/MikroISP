package com.loogika.mikroisp.network.exception

import com.loogika.mikroisp.network.di.model.error.ErrorModel

class CustomException(
    val error: ErrorModel
): Throwable()