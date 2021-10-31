package com.example.cache.converters

import androidx.room.TypeConverter
import com.example.cache.models.AuthorEntityDetails
import com.example.cache.models.ReviewEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object AuthorDetailsConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromAuthorDetails(authorDetails: AuthorEntityDetails): String {
        val type = object : TypeToken<AuthorEntityDetails>() {}.type
        return gson.toJson(authorDetails, type)
    }

    @TypeConverter
    fun toReviews(authorDetails: String?): AuthorEntityDetails {
        val type = object : TypeToken<List<ReviewEntity>?>() {}.type
        return gson.fromJson(authorDetails, type)
    }
}
