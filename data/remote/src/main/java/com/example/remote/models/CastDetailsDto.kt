package com.example.remote.models

data class CastDetailsDto(
    val cast: List<CastDto>,
    val crew: List<CrewDto>,
    val id: Int
)
