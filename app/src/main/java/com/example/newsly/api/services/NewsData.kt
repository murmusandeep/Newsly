package com.example.newsly.api.services

import com.example.newsly.api.models.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//https://newsapi.org/v2/top-headlines?country=in&apiKey
//https://newsapi.org/v2/everything?q=apple&from=2021-12-20&to=2021-12-20&sortBy=popularity&apiKey

const val BASE_URL = "https://newsapi.org/"
const val API_KEY = "e34ca36ad7444fa4bdc30c24afda52d8"

interface NewsData {

    //https://newsapi.org/v2/top-headlines?apiKey=$API_KEY&country=in&page=1
    @GET("v2/top-headlines?apiKey=$API_KEY")
    fun getHeadlines(@Query("country") country : String,@Query("page") page : Int) : Call<News>
}