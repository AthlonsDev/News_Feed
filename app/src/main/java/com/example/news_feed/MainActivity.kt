package com.example.news_feed

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kwabenaberko.newsapilib.NewsApiClient

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val newsApiClient = NewsApiClient("YOUR_API_KEY")
    }
}