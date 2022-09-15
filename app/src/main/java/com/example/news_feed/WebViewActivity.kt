package com.example.news_feed

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.web_view_layout.*

class WebViewActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.web_view_layout)


        val newsLink = intent.getStringExtra(NewsEverythingRow.NEWS_KEY)

//        Enables javascript on the webview
        web_view_news.settings.javaScriptEnabled = true
//        Enables overview mode
        web_view_news.settings.loadWithOverviewMode = true
//        Uses wide view port
        web_view_news.settings.useWideViewPort = true

        web_view_news.loadUrl(newsLink.toString())

        println("News Link: " + newsLink)


    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }



}