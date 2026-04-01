package com.example.pexelpinterest.data.repository

import com.example.pexelpinterest.data.database.PhotoEntity
import com.example.pexelpinterest.data.retrofit.models.PhotoApi
import com.example.pexelpinterest.data.retrofit.models.PhotoResponse

import kotlinx.coroutines.flow.Flow


interface Repository {

    suspend fun insertBookmarkPhoto(photoEntity: PhotoEntity)
    suspend fun deleteBookmarkPhoto(photoEntity: PhotoEntity)
    fun getAllBookmarkPhotos(): Flow<List<PhotoEntity>>
    fun getBookmarkPhotoById(id: Int): Flow<PhotoEntity>

    suspend fun getCuratedPhotos(page: Int, perPage: Int): PhotoResponse
    suspend fun searchPhotos(query: String, page: Int, perPage: Int): PhotoResponse
    suspend fun getPhotoById(id: Long): PhotoApi
}