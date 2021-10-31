package com.example.domain.models

data class Cast(
    val cast_id: Int,
    val character: String,
    val id: Int,
    val name: String,
    val original_name: String,
    val profile_path: String?
)
