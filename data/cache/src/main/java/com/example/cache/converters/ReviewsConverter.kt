package com.example.cache.converters

import androidx.room.TypeConverter
import com.example.cache.models.ReviewEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object ReviewsConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromReviews(reviews: List<ReviewEntity>): String? {
        if (reviews.isNullOrEmpty()) return null

        val type = object : TypeToken<List<ReviewEntity>?>() {}.type
        return gson.toJson(reviews, type)
    }

    @TypeConverter
    fun toReviews(reviews: String?): List<ReviewEntity>? {
        if (reviews.isNullOrEmpty()) return null

        val type = object : TypeToken<List<ReviewEntity>?>() {}.type
        return gson.fromJson(reviews, type)
    }
}
