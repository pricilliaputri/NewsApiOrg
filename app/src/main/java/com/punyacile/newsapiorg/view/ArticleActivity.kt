package com.punyacile.newsapiorg.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.punyacile.newsapiorg.R
import com.punyacile.newsapiorg.adapter.ArticleAdapter
import com.punyacile.newsapiorg.databinding.ActivityArticleBinding
import com.punyacile.newsapiorg.viewmodel.ArticleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleActivity : AppCompatActivity() {

    lateinit var binding: ActivityArticleBinding
    lateinit var artadapter: ArticleAdapter
    lateinit var artviewmodel : ArticleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        artviewmodel = ViewModelProvider(this).get(ArticleViewModel::class.java)

        artadapter = ArticleAdapter(ArrayList())


        binding.rvArticle.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvArticle.adapter = artadapter

        val bundle = intent.extras
        val getSource = bundle?.getString("source", "") ?: ""

        artviewmodel.callApiArticle(getSource)

        artviewmodel.getDataArticle().observe(this, Observer { list ->
            list?.let {
                artadapter.setDataArticle(it)
            }
        })


        artadapter.onClickArt = {
            val bundle = Bundle().apply {
                putString("url", it.url)
            }
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }

    }
}