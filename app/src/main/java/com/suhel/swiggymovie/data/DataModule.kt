package com.suhel.swiggymovie.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun providesOkHttp(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().also {
                it.level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

    @Provides
    @Singleton
    fun providesJsonConverter(): Converter.Factory =
        Json {
            ignoreUnknownKeys = true
        }.asConverterFactory("application/json".toMediaType())

    @Provides
    @Singleton
    fun providesApi(okHttpClient: OkHttpClient, jsonConverterFactory: Converter.Factory): Api =
        Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(jsonConverterFactory)
            .baseUrl("http://www.omdbapi.com")
            .build()
            .create()
}
