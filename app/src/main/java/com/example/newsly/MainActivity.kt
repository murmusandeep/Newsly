package com.example.newsly

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.newsly.adapters.NewsAdapter
import com.example.newsly.api.models.Article
import com.example.newsly.api.models.News
import com.example.newsly.api.services.NewsService
import com.example.newsly.helper.ColorPicker
import com.littlemango.stacklayoutmanager.StackLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var adapter: NewsAdapter;

    lateinit var recyclerView: RecyclerView;

    private var articles = mutableListOf<Article>()

    lateinit var container: ConstraintLayout

    var pageNum = 1

    var totalResult = -1

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.newsList)
        container = findViewById(R.id.layoutContainer)

        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.action_bar)

        adapter = NewsAdapter(this@MainActivity, articles)
        recyclerView.adapter = adapter
        // recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

        val layoutManager: StackLayoutManager = StackLayoutManager(StackLayoutManager.ScrollOrientation.BOTTOM_TO_TOP)
        layoutManager.setPagerMode(true)
        layoutManager.setPagerFlingVelocity(3000)

        layoutManager.setItemChangedListener(object: StackLayoutManager.ItemChangedListener{
            override fun onItemChanged(position: Int) {

                container.setBackgroundColor(Color.parseColor(ColorPicker.getColor()))

                Log.d(TAG, "First Visible Item - ${layoutManager.getFirstVisibleItemPosition()}")
                Log.d(TAG, "Item Count - ${layoutManager.itemCount}")

                if(totalResult > layoutManager.itemCount && layoutManager.getFirstVisibleItemPosition() >= layoutManager.itemCount - 5) {

                    pageNum++
                    getNews()
                }
            }

        })

        recyclerView.layoutManager = layoutManager

        getNews()
    }

    private fun getNews() {

        Log.d(TAG, "Request sent for $pageNum ")

        val news : Call<News> = NewsService.newsData.getHeadlines("in", pageNum)

        news.enqueue(object : Callback<News>{
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news: News? = response.body()
                if(news != null) {
                    Log.d("Akriti", news.toString())
                    totalResult = news.totalResults
                    articles.addAll(news.articles)
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("Sandeep", "Error in fetch news", t)
            }
        })
    }
}