package com.example.pexelpinterest.data.retrofit.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PhotoResponse(
    val page: Int,
    val perPage: Int,
    val photoApis: List<PhotoApi>,
    val totalResults: Int,
    val next_page: String?
)