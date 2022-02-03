package com.example.liza

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class WebActivity:AppCompatActivity() {
    var urlText = "https://greenslots.ru/WkyDrQSN"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        val web = findViewById<WebView>(R.id.web)
        web.webViewClient = Browser()
        web.settings.loadsImagesAutomatically = true
        web.settings.javaScriptEnabled = true
        web.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        web.loadUrl(intent?.getStringExtra("Link")?:urlText)
    }

    private class Browser : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
        }
    }
}