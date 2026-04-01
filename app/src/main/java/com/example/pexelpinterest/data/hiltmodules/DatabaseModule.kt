package com.example.pexelpinterest.data.hiltmodules

import android.content.Context
import androidx.room.Room
import com.example.pexelpinterest.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "pexel_app_database"
    ).build()

    @Singleton
    @Provides
    fun provideDao(db: AppDatabase) = db.bookmarkDao()
}