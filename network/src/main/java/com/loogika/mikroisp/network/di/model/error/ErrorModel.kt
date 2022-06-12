package com.loogika.mikroisp.network.di.model.error

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ErrorModel(val code: String?, val title: String?, val message: String) : Parcelable