package com.punyacile.newsapiorg.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.punyacile.newsapiorg.R
import com.punyacile.newsapiorg.databinding.ActivityCategoryBinding
import com.punyacile.newsapiorg.model.CategoryData
import com.punyacile.newsapiorg.adapter.CategoryAdapter
import dagger.hilt.android.AndroidEntryPoint

class CategoryActivity : AppCompatActivity() {

    lateinit var binding: ActivityCategoryBinding
    lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listCategory = arrayListOf(
            CategoryData("BUSINESS", R.drawable.business),
            CategoryData("ENTERTAINMENT",R.drawable.entertainment),
            CategoryData("GENERAL",R.drawable.general),
            CategoryData("HEALTH",R.drawable.health),
            CategoryData("SCIENCE",R.drawable.science),
            CategoryData("SPORTS",R.drawable.sports),
            CategoryData("TECHNOLOGY",R.drawable.technology)
        )

        categoryAdapter = CategoryAdapter(listCategory)

        binding.rvCategory.apply{
            layoutManager = LinearLayoutManager(this@CategoryActivity, LinearLayoutManager.VERTICAL, false)
            adapter = categoryAdapter
            categoryAdapter.onClick={
                var categ = it.name
                val inten = Intent(context, SourceActivity::class.java)
                inten.putExtra("name",categ)
                startActivity(inten)
            }
        }


    }
}