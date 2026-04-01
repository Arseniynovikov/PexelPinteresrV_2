package com.example.pexelpinterest.domain

data class Photo(
    val id: Long,
    val author: String,
    val srcOriginal: String,
    val srcMedium: String,
    val srcSmall: String,
    val url: String
)