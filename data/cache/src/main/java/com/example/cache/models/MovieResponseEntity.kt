package com.example.cache.models

data class MovieResponseEntity(
    val page: Int,
    val results: List<MovieEntity>,
)
