package com.prod.domain.model

sealed class ResultState<T> {
    class Success<T>(val data: T) : ResultState<T>()
    class Error<T>(val code: Int = -1, val exception: Throwable) : ResultState<T>()
}