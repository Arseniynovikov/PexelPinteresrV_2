package com.example.pexelpinterest.domain

import android.util.Log
import com.example.pexelpinterest.data.database.Dao
import com.example.pexelpinterest.domain.uistates.PhotoLoadingResult
import com.example.pexelpinterest.data.repository.Repository
import com.example.pexelpinterest.data.retrofit.PexelApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class RepositoryImpl @Inject constructor(
    val dao: Dao,
    val pexelApi: PexelApi
) : Repository {
    override suspend fun insertBookmarkPhoto(photo: Photo) {
        dao.insertBookmarkPhoto(photo.toEntity())
    }

    override suspend fun deleteBookmarkPhoto(photo: Photo) {
        dao.deleteBookmarkPhoto(photo.toEntity())
    }

    override fun getAllBookmarkPhotos(): Flow<List<Photo>> =
        dao.getAllBookmarkPhotos().map { it -> it.map { photoEntity -> photoEntity.toDomain() } }


    override suspend fun getCuratedPhotos(
        page: Int,
        perPage: Int
    ): Flow<List<Photo>> = flow {
        emit(pexelApi.getCuratedPhotos(page, perPage).photoApis.map { it ->
            it.toDomain()
        })
    }


    override suspend fun searchPhotos(
        query: String,
        page: Int,
        perPage: Int
    ): Flow<List<Photo>> = flow {
        emit(pexelApi.searchPhotos(query, page, perPage).photoApis.map { it -> it.toDomain() })
    }


    private suspend fun getPhotoById(id: Long): Photo =
        pexelApi.getPhotoById(id).toDomain()


    private fun getBookmarkPhotoById(id: Long): Flow<Photo?> =
        dao.getBookmarkPhotoById(id).map { photoEntity -> photoEntity?.toDomain() }


    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getPhotoResultById(id: Long): Flow<PhotoLoadingResult> {
        return getBookmarkPhotoById(id).flatMapLatest { photo ->
            if (photo != null) {
                Log.e("RepositoryImpGetPhotoResultByIdCath", "photo = ${photo.id}")
                flowOf(PhotoLoadingResult(photo = photo, isBookmark = true))
            } else {
                flow {
                    emit(PhotoLoadingResult(isLoading = true))

                    try {
                        val photoFromApi = getPhotoById(id)
                        Log.e(
                            "RepositoryImpGetPhotoResultByIdCath",
                            "photoFromApi = ${photoFromApi.id}"
                        )
                        emit(
                            PhotoLoadingResult(
                                photo = photoFromApi,
                                isBookmark = false,
                                isLoading = false
                            )
                        )
                    } catch (e: Exception) {
                        Log.e("RepositoryImpGetPhotoResultByIdCath", e.message.toString())
                        emit(
                            PhotoLoadingResult(
                                photo = null,
                                isBookmark = false,
                                isLoading = false
                            )
                        )
                    }
                }
            }
        }
    }
}