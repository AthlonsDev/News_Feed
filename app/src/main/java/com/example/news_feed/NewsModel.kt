package com.example.news_feed

class NewsModel(var title: String, val Source: String, val Author: String, val Content: String, val PublishedAt: String) {
    constructor() : this("", "", "", "", "")
}

class modelArray(
    var title: String
)