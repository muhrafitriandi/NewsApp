package com.yandey.utils.result

sealed class UIState<out T> {
    data object Idle : UIState<Nothing>()

    data object Loading : UIState<Nothing>()

    data class Success<out T>(val data: T) : UIState<T>()

    data class Error(val throwable: Throwable, val message: String,) : UIState<Nothing>()

    companion object {
        fun <T> idle(): UIState<T> = Idle

        fun <T> loading(): UIState<T> = Loading

        fun <T> success(data: T): UIState<T> = Success(data)

        fun <T> error(throwable: Throwable, message: String): UIState<T> = Error(throwable = throwable, message = message)
    }
}