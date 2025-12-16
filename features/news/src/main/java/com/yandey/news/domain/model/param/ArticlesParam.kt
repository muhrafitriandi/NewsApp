package com.yandey.news.domain.model.param

data class ArticlesParam(
    val sources: String?,
    val query: String?,
    val apiKey: String? = null,
)
