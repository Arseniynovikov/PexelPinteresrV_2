package com.example.pexelpinterest.domain.uistates

import com.example.pexelpinterest.domain.Photo

data class PhotoLoadingResult(
    val photo: Photo? = null,
    val isBookmark: Boolean = false,
    val isLoading: Boolean = false
)

data class PhotosLoadingResult(
    val photos: List<Photo> = emptyList(),
    val isLoading: Boolean = false
)