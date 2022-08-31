package com.example.news_feed

class NewsModel(val title: String, val Source: String, val Author: String, val Content: String, val PublishedAt: String) {
    constructor() : this("", "", "", "", "")
}