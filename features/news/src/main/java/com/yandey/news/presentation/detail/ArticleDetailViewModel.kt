package com.yandey.news.presentation.detail

import androidx.lifecycle.ViewModel
import com.yandey.designsystem.webview.WebViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.*

@HiltViewModel
class ArticleDetailViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(WebViewState())
    val state = _state.asStateFlow()

    fun onPageStarted() {
        _state.update {
            it.copy(
                isLoading = true,
                hasError = false,
                errorMessage = null
            )
        }
    }

    fun onPageFinished() {
        _state.update {
            it.copy(isLoading = false)
        }
    }

    fun onError(message: String) {
        _state.update {
            it.copy(
                isLoading = false,
                hasError = true,
                errorMessage = message
            )
        }
    }
}
