package com.example.news_feed

import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.news_row_layout.view.*

class NewsEverythingRow(val model: modelArray): Item<GroupieViewHolder>() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

        println("This is the news title: $model.title")

        viewHolder.itemView.Title.text = model.title
//        viewHolder.itemView.Date.text = model.PublishedAt

    }

    override fun getLayout(): Int {
        return R.layout.news_row_layout
    }


}