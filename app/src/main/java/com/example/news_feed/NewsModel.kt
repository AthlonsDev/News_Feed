package com.example.news_feed

class NewsModel(val Title: String, val Source: String, val Author: String, val Content: String, val PublishedAt: String) {
    constructor() : this("", "", "", "", "")
}