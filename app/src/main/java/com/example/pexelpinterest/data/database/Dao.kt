package com.example.pexelpinterest.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmarkPhoto(photoEntity: PhotoEntity)

    @Delete
    suspend fun deleteBookmarkPhoto(photoEntity: PhotoEntity)

    @Query("select * from bookmark_photos")
    fun getAllBookmarkPhotos(): Flow<List<PhotoEntity>>

    @Query("select * from bookmark_photos where id = :id")
    fun getBookmarkPhotoById(id: Int): Flow<PhotoEntity>
}