package com.example.pexelpinterest.domain

import com.example.pexelpinterest.data.database.PhotoEntity
import com.example.pexelpinterest.data.retrofit.models.PhotoApi

fun PhotoEntity.toDomain(): Photo =
    Photo(
        id = this.id,
        author = this.author,
        url = this.url,
        srcOriginal = this.srcOriginal,
        srcMedium = this.srcMedium,
        srcSmall = this.srcSmall
    )

fun PhotoApi.toDomain() =
    Photo(
        id = this.id,
        author = this.photographer,
        url = this.url,
        srcOriginal = this.src.original,
        srcMedium = this.src.medium,
        srcSmall = this.src.small
    )

fun Photo.toEntity(): PhotoEntity =
    PhotoEntity(
        id = this.id,
        author = this.author,
        url = this.url,
        srcOriginal = this.srcOriginal,
        srcMedium = this.srcMedium,
        srcSmall = this.srcSmall
    )

