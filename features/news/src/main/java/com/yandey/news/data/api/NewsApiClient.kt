package com.yandey.news.data.api

import com.yandey.news.data.model.response.ArticlesResponse
import com.yandey.utils.query.QueryParams
import com.yandey.utils.routes.ApiRoutes
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiClient {
    @GET(ApiRoutes.News.GET_ARTICLES)
    suspend fun getArticles(
        @Query(QueryParams.SOURCES) sources: String?,
        @Query(QueryParams.Q) query: String?,
        @Query(QueryParams.PAGE) page: Int,
        @Query(QueryParams.PAGE_SIZE) pageSize: Int,
        @Query(QueryParams.API_KEY) apiKey: String,
    ): ArticlesResponse
}