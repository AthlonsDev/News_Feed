package com.example.news_feed

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.core.content.ContextCompat.createDeviceProtectedStorageContext
import androidx.core.content.ContextCompat.startActivity
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.news_row_layout.view.*


class NewsEverythingRow(val model: modelArray): Item<GroupieViewHolder>() {

    

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

        viewHolder.itemView.title_textview.text = model.title
//        viewHolder.itemView.source_textview.text = model.source
        viewHolder.itemView.date_textview.text = model.publishedAt
        var url = model.url
        viewHolder.itemView.link_to_news.setOnClickListener {
//            TODO: Go To Website containing the news
            if (!url.startsWith("http://") && !url.startsWith("https://"))
                url = "http://" + url;
            val openURL = Intent(android.content.Intent.ACTION_VIEW)
            openURL.data = Uri.parse(url)


//            context.context.startActivity(openURL)


        }

    }

    override fun getLayout(): Int {
        return R.layout.news_row_layout
    }


}

class DetailViewHolder(private val customView: View, var obj: modelArray? = null): GroupieViewHolder (customView) {

    companion object {
        val NEWS_DATA_KEY = "NEWS_DATA_LINK"
    }

    init {
        customView.setOnClickListener {
            val intent = Intent(customView.context, MainActivity::class.java)
//            intent.putExtra(NEWS_DATA_KEY, obj?.link)
            customView.context.startActivity(intent)
        }


    }


}