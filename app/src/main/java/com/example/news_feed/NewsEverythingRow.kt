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

    var url = String()

    companion object {
        val NEWS_KEY = "NEWS_KEY"
    }

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

        viewHolder.itemView.title_textview.text = model.title
        viewHolder.itemView.date_textview.text = model.publishedAt
        url = model.url


    }

    override fun getLayout(): Int {
        return R.layout.news_row_layout
    }


}