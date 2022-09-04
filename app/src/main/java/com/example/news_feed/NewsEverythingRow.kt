package com.example.news_feed

import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.news_row_layout.view.*

class NewsEverythingRow(val model: modelArray): Item<GroupieViewHolder>() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

        println("This is the news title: $model.title")

        viewHolder.itemView.title_textview.text = model.title
        viewHolder.itemView.source_textview.text = model.source
//        viewHolder.itemView.Date.text = model.PublishedAt

    }

    override fun getLayout(): Int {
        return R.layout.news_row_layout
    }


}