package com.codepath.articlesearch

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NutritionAdapter(private val context: Context, private val nutritionList: List<DisplayNutrition>) :
    RecyclerView.Adapter<NutritionAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_food, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val nutrition = nutritionList[position]
        holder.bind(nutrition)
    }

    override fun getItemCount() = nutritionList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val itemNameMain = itemView.findViewById<TextView>(R.id.itemNameMain)
        private val calorieValue = itemView.findViewById<TextView>(R.id.calorieValue)

        fun bind(nutrition: DisplayNutrition) {
            itemNameMain.text = nutrition.calorieText
            calorieValue.text = nutrition.calorieValue
        }
    }
}