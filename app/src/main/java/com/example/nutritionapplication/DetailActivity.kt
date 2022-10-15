package com.codepath.articlesearch

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val addItemBtn = findViewById<Button>(R.id.addItemDetail)

        addItemBtn.setOnClickListener {
            val itemNameEditText = findViewById<EditText>(R.id.itemNameEditText).text.toString()
            val calorieValueEditText = findViewById<EditText>(R.id.calorieValueEditText).text.toString()
            val calorieLabelDetail = findViewById<TextView>(R.id.calorieLabelDetail).text.toString()

            lifecycleScope.launch(IO) {
                (application as NutritionApplication).db.nutritionDao().insert(
                    NutritionEntity(itemNameEditText, calorieValueEditText, calorieLabelDetail))
            }

            val intent = Intent(this@DetailActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}