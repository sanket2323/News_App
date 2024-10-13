package com.example.news_app.ui.compo

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun NewsArticlePage(url:String){
    AndroidView(
        factory = {Context ->
            WebView(Context).apply {
                settings.javaScriptEnabled =true
                webViewClient = WebViewClient()
                loadUrl(url)
            }
        }
    )
}