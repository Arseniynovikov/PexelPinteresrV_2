package com.example.pexelpinterest.domain

import com.example.pexelpinterest.data.database.AppDatabase
import com.example.pexelpinterest.data.database.PhotoEntity
import com.example.pexelpinterest.data.repository.Repository
import com.example.pexelpinterest.data.retrofit.PexelApi

import com.example.pexelpinterest.data.retrofit.models.PhotoApi
import com.example.pexelpinterest.data.retrofit.models.PhotoResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    val database: AppDatabase,
    val pexelApi: PexelApi
): Repository {
    override suspend fun insertBookmarkPhoto(photoEntity: PhotoEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteBookmarkPhoto(photoEntity: PhotoEntity) {
        TODO("Not yet implemented")
    }

    override fun getAllBookmarkPhotos(): Flow<List<PhotoEntity>> {
        TODO("Not yet implemented")
    }

    override fun getBookmarkPhotoById(id: Int): Flow<PhotoEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun getCuratedPhotos(
        page: Int,
        perPage: Int
    ): PhotoResponse {
        TODO("Not yet implemented")
    }

    override suspend fun searchPhotos(
        query: String,
        page: Int,
        perPage: Int
    ): PhotoResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getPhotoById(id: Long): PhotoApi {
        TODO("Not yet implemented")
    }
}