package com.example.news_feed

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.kwabenaberko.newsapilib.NewsApiClient
import com.kwabenaberko.newsapilib.NewsApiClient.ArticlesResponseCallback
import com.kwabenaberko.newsapilib.NewsApiClient.SourcesCallback
import com.kwabenaberko.newsapilib.models.request.EverythingRequest
import com.kwabenaberko.newsapilib.models.request.SourcesRequest
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest
import com.kwabenaberko.newsapilib.models.response.ArticleResponse
import com.kwabenaberko.newsapilib.models.response.SourcesResponse
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.forEach as forEach1


class MainActivity : AppCompatActivity() {

    val adapter = GroupAdapter<GroupieViewHolder>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val API_KEY = "09222197a3ee462bb339130b1c2089c0"

        val newsApiClient = NewsApiClient(API_KEY)

//        headlines_button.setOnClickListener {
//            getHeadlines(newsApiClient)
//        }
//        news_button.setOnClickListener {
//            getEverything(newsApiClient)
//        }

//        getSources(newsApiClient)

        news_recycler_view.adapter = adapter

//        getEverything(newsApiClient)

            getHeadlines(newsApiClient)

        supportActionBar?.title = "Get The News"
    }

    companion object {
        val NEWS_KEY = "NEWS_KEY"
    }

    private fun getHeadlines(newsApiClient: NewsApiClient) {
        newsApiClient.getTopHeadlines(
            TopHeadlinesRequest.Builder()
                .q("NASA")
                .language("en")
                .build(),
            object : ArticlesResponseCallback {
                override fun onSuccess(response: ArticleResponse) {

                    response.articles.forEach1 {
                        println(it.urlToImage)
                        if(it.title != null && it.source != null) {
                            val news = modelArray(it.title, it.publishedAt, it.url)
                            adapter.add(NewsEverythingRow(news))

                            adapter.setOnItemClickListener { item, view ->
                                val newsItem = item as NewsEverythingRow
                                val url = item.url
                                val intent = Intent(Intent.ACTION_VIEW)
                                intent.putExtra(NEWS_KEY, url)
                                println(item.url)
//                                val intent = Intent(view.context, url)
//                                startActivity(intent)

//                                TODO: Add WebView Class

                                val intentWeb = Intent(view.context, WebViewActivity::class.java)
//                                insert url data on companion object
                                intentWeb.putExtra(NEWS_KEY, url)
                                startActivity(intentWeb)

                            }
                        }



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
                .q("NASA")
                .build(),
            object : ArticlesResponseCallback {
                override fun onSuccess(response: ArticleResponse) {

                    for (i in 0..response.articles.size - 1) {
                        Log.d("NewsPrint", response.articles[i].toString())

//                        adapter.add(NewsEverythingRow(NewsModel()))
                    }



//                    headline_text.text = response.articles[0].title
//                    if(response.articles[0].content != null){
//                        news_content.text = response.articles[0].content
//                    } else {
//                        news_content.text = "News Content Unavailable"
//                    }
//                    news_author.text = "Author: " + response.articles[0].author
//                    news_date.text = "Published At" + response.articles[0].publishedAt
//                    news_URL.text = "Link: " + response.articles[0].url
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