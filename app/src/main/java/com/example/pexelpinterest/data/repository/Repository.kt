package com.example.pexelpinterest.data.repository

import com.example.pexelpinterest.domain.Photo

import kotlinx.coroutines.flow.Flow


interface Repository {

    suspend fun insertBookmarkPhoto(photo: Photo)
    suspend fun deleteBookmarkPhoto(photo: Photo)
    fun getAllBookmarkPhotos(): Flow<List<Photo>>
    fun getBookmarkPhotoById(id: Int): Flow<Photo>

    suspend fun getCuratedPhotos(page: Int, perPage: Int): List<Photo>
    suspend fun searchPhotos(query: String, page: Int, perPage: Int): List<Photo>
    suspend fun getPhotoById(id: Long): Photo
}