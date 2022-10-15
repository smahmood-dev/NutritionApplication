package com.codepath.articlesearch

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.articlesearch.databinding.ActivityMainBinding
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private val nutritionList = mutableListOf<DisplayNutrition>()
    private lateinit var nutritionRecyclerView: RecyclerView
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        nutritionRecyclerView = findViewById(R.id.nutritionList)
        val nutritionAdapter = NutritionAdapter(this, nutritionList)
        nutritionRecyclerView.adapter = nutritionAdapter

        lifecycleScope.launch {
            (application as NutritionApplication).db.nutritionDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    DisplayNutrition(
                        entity.itemName,
                        entity.calorieValue,
                        entity.calorieText
                    )
                }.also { mappedList ->
                    nutritionList.addAll(mappedList)
                    nutritionAdapter.notifyDataSetChanged()
                }
            }
        }

        nutritionRecyclerView.layoutManager = LinearLayoutManager(this).also {
            val dividerItemDecoration = DividerItemDecoration(this, it.orientation)
            nutritionRecyclerView.addItemDecoration(dividerItemDecoration)
        }

        val addItem = findViewById<Button>(R.id.addItemMain)
        addItem.setOnClickListener {
            val intent = Intent(this@MainActivity, DetailActivity::class.java)
            startActivity(intent)
        }

    }
}