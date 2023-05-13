package com.punyacile.newsapiorg.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.punyacile.newsapiorg.model.article.Article
import com.punyacile.newsapiorg.model.article.ResponseArticles
import com.punyacile.newsapiorg.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(var api : ApiService): ViewModel() {
    lateinit var liveDataArticle : MutableLiveData<List<Article>?>

    init {
        liveDataArticle = MutableLiveData()
    }

    fun getDataArticle() : MutableLiveData<List<Article>?> {
        return liveDataArticle
    }

    fun callApiArticle(article : String) {
        api.gellAllArticles(article).enqueue(object : Callback<ResponseArticles> {
            override fun onResponse(
                call: Call<ResponseArticles>,
                response: Response<ResponseArticles>
            ) {

                if (response.isSuccessful) {
                    liveDataArticle.postValue(response.body()!!.articles)
                } else {
                    liveDataArticle.postValue(null)
                }


            }

            override fun onFailure(call: Call<ResponseArticles>, t: Throwable) {

                liveDataArticle.postValue(null)

            }

        })

    }
}