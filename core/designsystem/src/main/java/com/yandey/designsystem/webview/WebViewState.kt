package com.yandey.designsystem.webview

data class WebViewState(
    val isLoading: Boolean = true,
    val hasError: Boolean = false,
    val errorMessage: String? = null
)