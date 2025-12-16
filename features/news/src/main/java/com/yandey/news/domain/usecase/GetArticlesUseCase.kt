package com.yandey.news.domain.usecase

import androidx.paging.PagingData
import com.yandey.news.domain.model.param.ArticlesParam
import com.yandey.news.domain.model.response.Article
import com.yandey.news.domain.repository.NewsRepository
import com.yandey.utils.usecase.WithParamsUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetArticlesUseCase @Inject constructor(private val repository: NewsRepository): WithParamsUseCase<ArticlesParam, Flow<PagingData<Article>>> {
    override fun execute(param: ArticlesParam): Flow<PagingData<Article>> = repository.getArticles(param)
}