package com.example.newsly.api.models

data class News(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)