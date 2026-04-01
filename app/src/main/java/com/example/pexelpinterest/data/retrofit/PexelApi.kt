package com.example.pexelpinterest.data.retrofit

import com.example.pexelpinterest.data.retrofit.models.PhotoApi
import com.example.pexelpinterest.data.retrofit.models.PhotoResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PexelApi {

    @GET("v1/curated")
    suspend fun getCuratedPhotos(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): PhotoResponse

    @GET("v1/search")
    suspend fun searchPhotos(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): PhotoResponse

    @GET("v1/photos/{id}")
    suspend fun getPhotoById(@Path("id") id: Long): PhotoApi

}