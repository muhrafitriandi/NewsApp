package com.yandey.news.data.source.remote

import com.yandey.news.data.api.NewsApiClient
import com.yandey.news.data.model.response.ArticlesResponse
import javax.inject.Inject

class NewsRemoteDataSourceImpl @Inject constructor(private val newsApiClient: NewsApiClient) : NewsRemoteDataSource {
    override suspend fun getArticles(
        sources: String?,
        query: String?,
        page: Int,
        pageSize: Int,
        apiKey: String
    ): ArticlesResponse {
        return newsApiClient.getArticles(sources, query, page, pageSize, apiKey)
    }
}