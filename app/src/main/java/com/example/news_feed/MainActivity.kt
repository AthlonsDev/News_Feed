package com.example.news_feed

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import androidx.appcompat.app.AppCompatActivity
import com.example.news_feed.NewsEverythingRow.Companion.NEWS_KEY
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

    val API_KEY = "09222197a3ee462bb339130b1c2089c0"

    val newsApiClient = NewsApiClient(API_KEY)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



//        headlines_button.setOnClickListener {
//            getHeadlines(newsApiClient)
//        }
//        news_button.setOnClickListener {
//            getEverything(newsApiClient)
//        }

//        getSources(newsApiClient)

        news_recycler_view.adapter = adapter

//        getEverything(newsApiClient)
        val query = "NASA"

        getHeadlines(query)



        supportActionBar?.title = "Get The News"
    }



    private fun getHeadlines(query: String) {
        adapter.clear()
        newsApiClient.getTopHeadlines(
            TopHeadlinesRequest.Builder()
                .q(query.toString())
                .language("en")
                .build(),
            object : ArticlesResponseCallback {
                override fun onSuccess(response: ArticleResponse) {

                    response.articles.forEach1 {
                        println(it.urlToImage)
                        if(it.title != null && it.source != null) {
//                            init modelArray as news constant and complete with constructors
                            val news = modelArray(it.title, it.publishedAt, it.url)
//                             Add the new object(with assigned values) to the adapter(recyclerVIew)
                            adapter.add(NewsEverythingRow(news))

                            adapter.setOnItemClickListener { item, view ->
//                                Downcast item as NewsEverythingRow object
                                item as NewsEverythingRow
//                                assign item's url to constant
                                val url = item.url
//                                Specify nature of intent
                                val intent = Intent(Intent.ACTION_VIEW)
//                                Insert url in Companion Object
                                intent.putExtra(NEWS_KEY, url)
//                                declare intent to open class
                                val intentWeb = Intent(view.context, WebViewActivity::class.java)
//                                insert url data on companion object
                                intentWeb.putExtra(NEWS_KEY, url)
//                                start activity that opens the intent/class of webview
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
                    }

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


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        return super.onCreateOptionsMenu(menu)

        menuInflater.inflate(R.menu.menu_bar, menu)
        val searchItem = menu?.findItem(R.id.menu_search)

          val searchView = searchItem?.actionView as SearchView
            searchView.setOnQueryTextListener(object : OnQueryTextListener{
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    getHeadlines(p0.toString())
                    println(p0)
                    closeKeyBoard()
                    return true
                }

                override fun onQueryTextChange(p0: String?): Boolean {
//                    query = p0.toString()
                    return true
                }

            })

        return super.onCreateOptionsMenu(menu)

    }

    private fun closeKeyBoard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}