package com.yandey.news.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.yandey.news.data.paging.GetArticlesPagingSource
import com.yandey.news.data.source.remote.NewsRemoteDataSource
import com.yandey.news.domain.model.param.ArticlesParam
import com.yandey.news.domain.model.response.Article
import com.yandey.news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val remoteDataSource: NewsRemoteDataSource) : NewsRepository {
    override fun getArticles(param: ArticlesParam): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = DEFAULT_PAGE_SIZE, prefetchDistance = THRESHOLD_ITEM),
            pagingSourceFactory = {
                GetArticlesPagingSource(
                    remoteDataSource = remoteDataSource,
                    param = param.copy(apiKey = API_KEY),
                )
            }
        ).flow
    }

    companion object {
        private const val DEFAULT_PAGE_SIZE = 20
        private const val THRESHOLD_ITEM = 1
        private const val API_KEY = "7cc9c2d2ccb242b080d808037c43f1b4"
    }
}