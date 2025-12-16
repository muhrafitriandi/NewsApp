package com.yandey.news.domain.repository

import androidx.paging.PagingData
import com.yandey.news.domain.model.param.ArticlesParam
import com.yandey.news.domain.model.response.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getArticles(param: ArticlesParam): Flow<PagingData<Article>>
}