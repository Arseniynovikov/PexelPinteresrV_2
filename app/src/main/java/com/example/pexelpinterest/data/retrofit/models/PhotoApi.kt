package com.example.pexelpinterest.data.retrofit.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PhotoApi(
    val id: Long,
    val width: Int,
    val height: Int,
    val url: String,
    val photographer: String,
    val photographer_url: String,
    val photographer_id: Long,
    val avg_color: String?,
    val src: SourceInfo,
    val liked: Boolean
)