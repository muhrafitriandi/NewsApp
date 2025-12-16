package com.yandey.news.presentation.article

import androidx.compose.runtime.collectAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.yandey.designsystem.AppSearchBar
import com.yandey.designsystem.FilterChipGroup
import com.yandey.designsystem.PagingList
import com.yandey.news.presentation.article.components.ArticleItem
import com.yandey.news.utils.Constant

@Composable
fun ArticlesScreen(
    modifier: Modifier = Modifier,
    viewModel: ArticlesViewModel = hiltViewModel(),
    onOpenDetail: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val pagingItems = viewModel.articlesPaging.collectAsLazyPagingItems()

    Column(modifier = modifier.fillMaxSize()) {
        AppSearchBar(
            value = uiState.searchQuery,
            onValueChange = viewModel::onSearchQueryChanged,
            placeholder = "Search news..."
        )

        FilterChipGroup(
            items = Constant.NEWS_CATEGORY,
            selectedItem = uiState.selectedSource,
            onItemSelected = viewModel::onSourceSelected
        ) { category ->
            Text(category.uppercase())
        }

        PagingList(
            pagingItems = pagingItems,
            modifier = Modifier.weight(1f),
            onItemClick = { article ->
                onOpenDetail(article.url)
            },
            itemContent = { article ->
                ArticleItem(
                    article = article,
                    onClick = { onOpenDetail(article.url) }
                )
            }
        )
    }
}