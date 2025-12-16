package com.yandey.news.presentation.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.yandey.news.domain.model.param.ArticlesParam
import com.yandey.news.domain.model.response.Article
import com.yandey.news.domain.usecase.GetArticlesUseCase
import com.yandey.news.presentation.article.state.ArticlesUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import androidx.paging.cachedIn
import javax.inject.Inject

@HiltViewModel
class ArticlesViewModel @Inject constructor(
    private val getArticlesUseCase: GetArticlesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ArticlesUiState())
    val uiState = _uiState.asStateFlow()

    val articlesPaging: Flow<PagingData<Article>> =
        uiState
            .map { it.searchQuery to it.selectedSource }
            .debounce(DELAY_LOAD_NEWS)
            .distinctUntilChanged()
            .flatMapLatest { (query, sources) ->
                getArticlesUseCase(
                    ArticlesParam(
                        sources = sources.ifBlank { null },
                        query = query.ifBlank { null }
                    )
                )
            }
            .cachedIn(viewModelScope)

    fun onSearchQueryChanged(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
    }

    fun onSourceSelected(source: String) {
        _uiState.update { it.copy(selectedSource = source) }
    }

    companion object {
        const val DELAY_LOAD_NEWS = 500L
    }
}