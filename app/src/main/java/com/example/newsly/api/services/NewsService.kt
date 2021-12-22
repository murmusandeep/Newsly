package com.example.newsly.api.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NewsService {

    val newsData : NewsData;

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        newsData = retrofit.create(NewsData::class.java)
    }
}