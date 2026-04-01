package com.example.pexelpinterest.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmark_photos")
data class PhotoEntity(
    @PrimaryKey val id: Int,
    val author: String,
    val url: String,
    val srcOriginal: String,
    val srcSmall: String,
    val srcMedium: String
)