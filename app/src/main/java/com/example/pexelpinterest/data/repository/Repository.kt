package com.example.pexelpinterest.data.repository

import com.example.pexelpinterest.domain.Photo
import com.example.pexelpinterest.domain.uistates.PhotoLoadingResult

import kotlinx.coroutines.flow.Flow


interface Repository {

    suspend fun insertBookmarkPhoto(photo: Photo)
    suspend fun deleteBookmarkPhoto(photo: Photo)
    fun getAllBookmarkPhotos(): Flow<List<Photo>>


    suspend fun getCuratedPhotos(page: Int, perPage: Int): Flow<List<Photo>>
    suspend fun searchPhotos(query: String, page: Int, perPage: Int): Flow<List<Photo>>



    fun getPhotoResultById(id: Long): Flow<PhotoLoadingResult>
}