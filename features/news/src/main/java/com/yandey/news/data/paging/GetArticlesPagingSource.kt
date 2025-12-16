package com.yandey.news.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.yandey.news.data.mapper.toDomain
import com.yandey.news.data.source.remote.NewsRemoteDataSource
import com.yandey.news.domain.model.param.ArticlesParam
import com.yandey.news.domain.model.response.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetArticlesPagingSource @Inject constructor(
    private val remoteDataSource: NewsRemoteDataSource,
    private val param: ArticlesParam,
) : PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> = withContext(Dispatchers.IO){
        val currentPage = params.key ?: 1
        val currentSize = params.loadSize

        runCatching {
            val response = remoteDataSource.getArticles(
                page = currentPage,
                pageSize = currentSize,
                sources = param.sources,
                query = param.query,
                apiKey = param.apiKey.orEmpty()
            )

            val items = if (response.status == "ok") {
                response.toDomain().articles
            } else {
                emptyList()
            }

            val endOfPaginationReached = items.size < currentSize

            LoadResult.Page(
                data = items,
                prevKey = if (currentPage == 1) null else currentPage.minus(1),
                nextKey = if (endOfPaginationReached) null else currentPage.plus(1),
            )
        }.getOrElse { e ->
            if (e.toString().contains("No Record Found", true)) {
                LoadResult.Page(
                    data = listOf<Article>(),
                    prevKey = if (currentPage == 1) null else currentPage.minus(1),
                    nextKey = null,
                )
            } else {
                LoadResult.Error(e)
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? = state.anchorPosition?.let { anchorPosition ->
        state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1) ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
    }
}
