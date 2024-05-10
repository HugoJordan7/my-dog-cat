package com.example.mydogcat.service.repository

import com.example.mydogcat.model.Pet
import com.example.mydogcat.service.CatService
import com.example.mydogcat.service.DogService
import com.example.mydogcat.util.PetDetailsCallback
import com.example.mydogcat.util.PetsCallback

class PetRepository(private val catService: CatService, private val dogService: DogService) {

    suspend fun findDogs(callback: PetsCallback, limit: Int) {
        try {
            val dogs = dogService.findDogs(limit, hasBreeds = 1)
            callback.onSuccess(dogs)
        } catch (e: Exception) {
            callback.onFailure(e.message ?: "Unknown error")
        }
    }

    suspend fun findCats(callback: PetsCallback, limit: Int) {
        try {
            val cats = catService.findCats(limit)
            callback.onSuccess(cats)
        } catch (e: Exception) {
            callback.onFailure(e.message ?: "Unknown error")
        }
    }


    suspend fun findDogDetails(callback: PetDetailsCallback, pet: Pet) {
        try {
            val dogDetails = dogService.findDogDetails(pet.id)
            callback.onSuccess(dogDetails)
        } catch (e: Exception) {
            callback.onFailure(e.message.toString())
        }
    }

    suspend fun findCatDetails(callback: PetDetailsCallback, pet: Pet) {
        try {
            val catDetails = catService.findCatDetails(pet.id)
            callback.onSuccess(catDetails)
        } catch (e: Exception) {
            callback.onFailure(e.message.toString())
        }
    }

}