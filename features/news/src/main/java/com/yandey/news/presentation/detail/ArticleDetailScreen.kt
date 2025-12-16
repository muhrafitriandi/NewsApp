package com.yandey.news.presentation.detail

import android.webkit.WebView
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.collectAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.yandey.designsystem.webview.AppWebView
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.yandey.designsystem.box.FullScreenOverlay
import com.yandey.designsystem.error.ErrorComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleDetailScreen(
    url: String,
    onBack: () -> Unit,
    viewModel: ArticleDetailViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsState()

    val webView = remember { WebView(context) }

    BackHandler {
        if (webView.canGoBack()) webView.goBack()
        else onBack()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail") },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            if (webView.canGoBack()) webView.goBack()
                            else onBack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            AppWebView(
                webView = webView,
                url = url,
                modifier = Modifier.fillMaxSize(),
                onPageStarted = viewModel::onPageStarted,
                onPageFinished = viewModel::onPageFinished,
                onError = viewModel::onError
            )

            if (state.isLoading) {
                FullScreenOverlay { CircularProgressIndicator() }
            }

            if (state.hasError) {
                FullScreenOverlay {
                    ErrorComponent {
                        webView.loadUrl(url)
                        viewModel.onPageStarted()
                    }
                }
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            webView.stopLoading()
            webView.destroy()
        }
    }
}
