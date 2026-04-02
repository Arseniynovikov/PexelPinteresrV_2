package com.example.pexelpinterest.domain

import android.util.Log
import com.example.pexelpinterest.data.database.Dao
import com.example.pexelpinterest.data.repository.Repository
import com.example.pexelpinterest.data.retrofit.PexelApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    val dao: Dao,
    val pexelApi: PexelApi
): Repository {
    override suspend fun insertBookmarkPhoto(photo: Photo) {
        dao.insertBookmarkPhoto(photo.toEntity())
    }

    override suspend fun deleteBookmarkPhoto(photo: Photo) {
        dao.deleteBookmarkPhoto(photo.toEntity())
    }

    override fun getAllBookmarkPhotos(): Flow<List<Photo>> =
        dao.getAllBookmarkPhotos().map { it -> it.map { photoEntity -> photoEntity.toDomain() } }

    override fun getBookmarkPhotoById(id: Int): Flow<Photo> =
        dao.getBookmarkPhotoById(id).map { photoEntity -> photoEntity.toDomain() }

    override suspend fun getCuratedPhotos(
        page: Int,
        perPage: Int
    ): List<Photo> =
        pexelApi.getCuratedPhotos(page, perPage).photoApis.map { it ->
            it.toDomain()
        }


    override suspend fun searchPhotos(
        query: String,
        page: Int,
        perPage: Int
    ): List<Photo> =
        pexelApi.searchPhotos(query, page, perPage).photoApis.map{it -> it.toDomain()}


    override suspend fun getPhotoById(id: Long): Photo =
        pexelApi.getPhotoById(id).toDomain()
}