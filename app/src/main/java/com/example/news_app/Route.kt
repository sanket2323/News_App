package com.example.news_app

import kotlinx.serialization.Serializable

@Serializable
object HomePageScreen


@Serializable
data class NewsArticleScreen(
    val url:String
)
