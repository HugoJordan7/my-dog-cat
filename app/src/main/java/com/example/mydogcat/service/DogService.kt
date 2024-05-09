package com.example.mydogcat.service

import com.example.mydogcat.model.Pet
import com.example.mydogcat.model.PetDetails
import com.example.mydogcat.util.API_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DogService {
    @GET("search")
    suspend fun findDogs(
        @Query("limit") limit: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("has_breeds") hasBreeds: Int = 0
    ): List<Pet>

    @GET("{id}")
    suspend fun findDogDetails(
        @Path("id") id: String,
        @Query("api_key") apiKey: String = API_KEY
    ): PetDetails
}