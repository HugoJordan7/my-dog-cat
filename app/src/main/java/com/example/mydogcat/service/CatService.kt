package com.example.mydogcat.service

import com.example.mydogcat.model.Pet
import com.example.mydogcat.model.PetDetails
import com.example.mydogcat.util.API_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CatService {

    @GET("search")
    fun findCats(
        @Query("limit") limit: Int
    ): Call<List<Pet>>

    @GET("{id}")
    fun findCatDetails(
        @Path("id") id: String,
        @Query("api_key") apiKey: String = API_KEY
    ): Call<PetDetails>

}