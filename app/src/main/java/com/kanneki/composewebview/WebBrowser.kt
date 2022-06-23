package com.kanneki.composewebview

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.util.Log
import android.webkit.WebView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.google.accompanist.web.*

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebBrowser() {

    val state = rememberWebViewState(url = "https://www.google.com.tw")
    val navigator = rememberWebViewNavigator()

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar {
            IconButton(onClick = { navigator.navigateBack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
            IconButton(onClick = { navigator.navigateForward() }) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Forward"
                )
            }
            Text(
                text = "Web Browser",
                style = TextStyle(
                    color = Color.White,
                    fontSize = MaterialTheme.typography.h6.fontSize,
                    fontWeight = MaterialTheme.typography.h6.fontWeight
                )
            )
        }

        Row(modifier = Modifier.padding(12.dp)) {
            val webClient = remember {
                object : AccompanistWebViewClient() {

                    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                        super.onPageStarted(view, url, favicon)
                        Log.d("pearce", "onPageStarted: loading for: $url")
                    }
                }
            }

            WebView(
                state = state,
                modifier = Modifier.weight(1f),
                navigator = navigator,
                onCreated = { webView ->
                    webView.settings.javaScriptEnabled = true
                },
                client = webClient
            )
        }
    }
}

