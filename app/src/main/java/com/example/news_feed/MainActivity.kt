package com.example.news_feed

import android.os.Bundle
import android.os.Debug
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.kwabenaberko.newsapilib.NewsApiClient
import com.kwabenaberko.newsapilib.NewsApiClient.ArticlesResponseCallback
import com.kwabenaberko.newsapilib.NewsApiClient.SourcesCallback
import com.kwabenaberko.newsapilib.models.request.EverythingRequest
import com.kwabenaberko.newsapilib.models.request.SourcesRequest
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest
import com.kwabenaberko.newsapilib.models.response.ArticleResponse
import com.kwabenaberko.newsapilib.models.response.SourcesResponse



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val API_KEY = "09222197a3ee462bb339130b1c2089c0"

        val newsApiClient = NewsApiClient(API_KEY)

        getSources(newsApiClient)
        getHeadlines(newsApiClient)
    }


    private fun getHeadlines(newsApiClient: NewsApiClient) {
        println("Reading API")
        newsApiClient.getTopHeadlines(
            TopHeadlinesRequest.Builder()
                .q("Italy")
                .language("en")
                .build(),
            object : ArticlesResponseCallback {
                override fun onSuccess(response: ArticleResponse) {
                    for (i in 0..1) {
                        Log.d("NewsPrint", response.articles[i].title)
                    }

                }
                override fun onFailure(throwable: Throwable) {
                    Log.d("NewsPrint", throwable.message.toString())
                }
            }
        )
    }

    private fun getEverything(newsApiClient: NewsApiClient) {
        newsApiClient.getEverything(
            EverythingRequest.Builder()
                .q("trump")
                .build(),
            object : ArticlesResponseCallback {
                override fun onSuccess(response: ArticleResponse) {
                    println(response.articles[0].title)
                }

                override fun onFailure(throwable: Throwable) {
                    println(throwable.message)
                }
            }
        )
    }

    private  fun getSources(newsApiClient: NewsApiClient) {
        newsApiClient.getSources(
            SourcesRequest.Builder()
                .language("en")
                .country("us")
                .build(),
            object : SourcesCallback {
                override fun onSuccess(response: SourcesResponse) {
                    Log.d("NewsPrint", response.sources[0].name)
                }

                override fun onFailure(throwable: Throwable) {
                    println(throwable.message)
                }
            }
        )
    }


}