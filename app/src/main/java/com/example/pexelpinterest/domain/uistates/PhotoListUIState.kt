package com.example.pexelpinterest.domain.uistates

import com.example.pexelpinterest.domain.Photo


data class PhotoListUIState(
    val photos: List<Photo> = emptyList(),
    val query: String = "",
    val isLoading: Boolean = false,
    val isSearchActive: Boolean = false,
    val errorMes: String? = null
)