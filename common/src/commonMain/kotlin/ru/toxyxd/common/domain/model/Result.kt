package ru.toxyxd.common.domain.model

sealed interface Result<T : Any>

data class Success<T : Any>(val data: T) : Result<T>
data class Error<T : Any>(val code: Int, val message: String?) : Result<T>
data class Exception<T : Any>(val exception: Throwable) : Result<T>
