package com.yandey.designsystem.webview

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun AppWebView(
    webView: WebView,
    url: String,
    modifier: Modifier = Modifier,
    onPageStarted: () -> Unit = {},
    onPageFinished: () -> Unit = {},
    onError: (String) -> Unit = {},
    configure: (WebSettings.() -> Unit)? = null
) {
    AndroidView(
        modifier = modifier,
        factory = { webView },
        update = { view ->
            view.settings.apply {
                javaScriptEnabled = true
                domStorageEnabled = true
                loadWithOverviewMode = true
                useWideViewPort = true
                builtInZoomControls = false
                displayZoomControls = false

                configure?.invoke(this)
            }

            view.webViewClient = object : WebViewClient() {
                override fun onPageStarted(
                    view: WebView?,
                    url: String?,
                    favicon: Bitmap?
                ) {
                    onPageStarted()
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    onPageFinished()
                }

                override fun onReceivedError(
                    view: WebView?,
                    request: WebResourceRequest?,
                    error: WebResourceError?
                ) {
                    if (request?.isForMainFrame == true) {
                        view?.stopLoading()
                        onError(error?.description?.toString().orEmpty())
                    }
                }
            }

            if (view.url != url) {
                view.loadUrl(url)
            }
        }
    )
}