package com.example.mydogcat.data

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HTTPData {
    private const val DOG_BASE_URL = "https://api.thedogapi.com/v1/images/"
    private const val CAT_BASE_URL = "https://api.thecatapi.com/v1/images/"
    const val API_KEY = "live_rW02IiF5C76bb7qw16o7Swiq9LfsBSz4FgCnnBgUA0SYCzuIhgkyeRPWeUKhudtM"

    fun dogRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(DOG_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun catRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(CAT_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}