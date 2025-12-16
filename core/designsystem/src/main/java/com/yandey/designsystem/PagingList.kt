package com.yandey.designsystem

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems

@Composable
fun <T : Any> PagingList(
    pagingItems: LazyPagingItems<T>,
    modifier: Modifier = Modifier,
    onItemClick: (T) -> Unit = {},
    itemContent: @Composable (T) -> Unit,
    loadingContent: @Composable () -> Unit = {
        CircularProgressIndicator()
    },
    errorContent: @Composable (Throwable, onRetry: () -> Unit) -> Unit = { _, onRetry ->
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Something went wrong")
            Spacer(Modifier.height(8.dp))
            Button(onClick = onRetry) {
                Text("Retry")
            }
        }
    },
    inlineErrorContent: @Composable (Throwable, onRetry: () -> Unit) -> Unit = { _, onRetry ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Failed to load data",
                color = MaterialTheme.colorScheme.error
            )
            TextButton(onClick = onRetry) {
                Text("Retry")
            }
        }
    },
    emptyContent: @Composable () -> Unit = {
        Text("Data not found")
    },
    endOfListContent: @Composable () -> Unit = {
        Text(
            text = "- You've reached the end -",
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
) {
    val loadState = pagingItems.loadState

    val isRefreshing = loadState.refresh is LoadState.Loading
    val refreshError = loadState.refresh as? LoadState.Error
    val appendError = loadState.append as? LoadState.Error

    val isEmpty =
        pagingItems.itemCount == 0 &&
                loadState.refresh is LoadState.NotLoading &&
                loadState.append.endOfPaginationReached

    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn {
            items(pagingItems.itemCount) { index ->
                pagingItems[index]?.let { item ->
                    Box(
                        modifier = Modifier.clickable { onItemClick(item) }
                    ) {
                        itemContent(item)
                    }
                }
            }

            // APPEND LOADING
            if (loadState.append is LoadState.Loading) {
                item {
                    CenterBox { loadingContent() }
                }
            }

            // APPEND ERROR -> retry()
            appendError?.let {
                item {
                    inlineErrorContent(it.error) {
                        pagingItems.retry()
                    }
                }
            }

            // END OF LIST
            if (
                loadState.append.endOfPaginationReached &&
                pagingItems.itemCount > 0
            ) {
                item {
                    endOfListContent()
                }
            }
        }

        // FULL REFRESH LOADING
        if (isRefreshing) {
            FullScreenOverlay { loadingContent() }
        }

        // FULL ERROR -> refresh()
        if (refreshError != null && pagingItems.itemCount == 0) {
            FullScreenOverlay {
                errorContent(refreshError.error) {
                    pagingItems.refresh()
                }
            }
        }

        // EMPTY
        if (isEmpty) {
            FullScreenOverlay { emptyContent() }
        }
    }
}

@Composable
private fun CenterBox(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) { content() }
}

@Composable
private fun FullScreenOverlay(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) { content() }
}
