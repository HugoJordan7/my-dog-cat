package com.example.mydogcat.di

import com.example.mydogcat.service.CatService
import com.example.mydogcat.service.DogService
import com.example.mydogcat.util.CAT_BASE_URL
import com.example.mydogcat.util.DOG_BASE_URL
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { providerDogRetrofit() }
    single { providerCatRetrofit() }
}

fun providerDogRetrofit(): DogService = Retrofit.Builder()
    .baseUrl(DOG_BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(DogService::class.java)

fun providerCatRetrofit(): CatService = Retrofit.Builder()
    .baseUrl(CAT_BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(CatService::class.java)