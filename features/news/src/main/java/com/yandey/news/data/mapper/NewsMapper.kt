package com.yandey.news.data.mapper

import com.yandey.news.data.model.response.ArticleResponse
import com.yandey.news.data.model.response.ArticleSourceResponse
import com.yandey.news.data.model.response.ArticlesResponse
import com.yandey.news.domain.model.response.Article
import com.yandey.news.domain.model.response.ArticleSource
import com.yandey.news.domain.model.response.Articles

fun ArticlesResponse.toDomain(): Articles {
    return Articles(
        status = status.orEmpty(),
        totalResults = totalResults ?: 0,
        articles = articles?.map { it.toDomain() }.orEmpty()
    )
}

fun ArticleResponse.toDomain(): Article {
    return Article(
        source = source?.toDomain() ?: ArticleSource(
            id = "",
            name = ""
        ),
        author = author.orEmpty(),
        title = title.orEmpty(),
        description = description.orEmpty(),
        url = url.orEmpty(),
        urlToImage = urlToImage.orEmpty(),
        publishedAt = publishedAt.orEmpty(),
        content = content.orEmpty()
    )
}

fun ArticleSourceResponse.toDomain(): ArticleSource {
    return ArticleSource(
        id = id.orEmpty(),
        name = name.orEmpty()
    )
}