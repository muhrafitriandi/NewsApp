package com.yandey.news.presentation.article.state

import com.yandey.news.utils.Constant

data class ArticlesUiState(
    val selectedSource: String = Constant.NEWS_CATEGORY.first(),
    val searchQuery: String = "",
    val isRefreshing: Boolean = false
)