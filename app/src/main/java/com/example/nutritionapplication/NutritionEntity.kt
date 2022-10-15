package com.codepath.articlesearch

import androidx.room.Entity
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

@Entity(tableName = "nutrition_table")
data class NutritionEntity (
    @ColumnInfo(name = "itemName") val itemName: String?,
    @ColumnInfo(name = "caloriesValue") val calorieValue: String?,
    @ColumnInfo(name = "caloriesText") val calorieText: String?,
    @PrimaryKey(autoGenerate = true) val id: Long = 0
)