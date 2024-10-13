package com.example.news_app.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.news_app.constant.Apikey
import com.kwabenaberko.newsapilib.NewsApiClient
import com.kwabenaberko.newsapilib.models.Article
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest
import com.kwabenaberko.newsapilib.models.response.ArticleResponse


class MainViewModel:ViewModel() {

    private var _articles = MutableLiveData<List<Article>>()
    val articles:LiveData<List<Article>> = _articles

    init {
        fetchNewsTopHeadlines()
    }

    fun fetchNewsTopHeadlines(category:String? = "GENERAL".toString()){
        val newsApiClient = NewsApiClient(Apikey.api_key)

        val request = TopHeadlinesRequest.Builder().language("en").category(category).build()

        newsApiClient.getTopHeadlines(request, object : NewsApiClient.ArticlesResponseCallback{
            override fun onSuccess(response: ArticleResponse?) {
                response?.articles?.let {
                    _articles.postValue(it)

                }
            }

            override fun onFailure(throwable: Throwable?) {
                if (throwable != null) {
                    Log.i("NewsAPI Response Failed",throwable.localizedMessage)
                }
            }

        })

    }

}