package com.example.pexelpinterest.data.retrofit.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.serialization.SerialName

@JsonClass(generateAdapter = true)
data class PhotoResponse(
    val page: Int,
    @Json(name = "per_page")
    val perPage: Int,
    @Json(name = "photos")
    val photoApis: List<PhotoApi>,
    @Json(name="total_results")
    val totalResults: Int,
    val next_page: String?
)