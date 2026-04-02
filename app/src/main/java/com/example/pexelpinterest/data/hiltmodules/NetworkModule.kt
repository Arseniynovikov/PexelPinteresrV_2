package com.example.pexelpinterest.data.hiltmodules

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import javax.inject.Singleton
import com.example.pexelpinterest.BuildConfig
import com.example.pexelpinterest.PexelApp
import com.example.pexelpinterest.data.retrofit.PexelApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesMoshi(): Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    @Provides
    @Singleton
    fun providesAuthInterceptor(): Interceptor = Interceptor { chain ->
        val request =
            chain.request().newBuilder().header("Authorization", BuildConfig.PEXELS_API_KEY).build()
        chain.proceed(request = request)
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(interceptor: Interceptor): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(interceptor).build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, moshi: Moshi): Retrofit =
        Retrofit.Builder().baseUrl("https://api.pexels.com").addConverterFactory(
            MoshiConverterFactory.create(moshi)
        ).build()

    @Provides
    @Singleton
    fun providePexelApi(retrofit: Retrofit): PexelApi = retrofit.create(PexelApi::class.java)
}