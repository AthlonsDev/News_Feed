package com.example.news_feed

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

class NewsModel(var title: String, val Source: String, val Author: String, val Content: String, val PublishedAt: String) {
    constructor() : this("", "", "", "", "")
}

@Parcelize
class modelArray(
    var title: String,
    var publishedAt: String,
    var url: String
): Parcelable