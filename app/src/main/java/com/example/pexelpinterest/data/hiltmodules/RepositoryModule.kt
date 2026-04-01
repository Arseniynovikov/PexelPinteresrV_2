package com.example.pexelpinterest.data.hiltmodules

import com.example.pexelpinterest.data.repository.Repository
import com.example.pexelpinterest.domain.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule{
    @Binds
    abstract fun bindPhotoRepository(impl: RepositoryImpl): Repository
}