package com.example.mydogcat.service.repository

import com.example.mydogcat.model.Pet
import com.example.mydogcat.service.CatService
import com.example.mydogcat.service.DogService
import com.example.mydogcat.service.PetAPI
import com.example.mydogcat.util.CATS
import com.example.mydogcat.util.DOGS
import com.example.mydogcat.util.PetDetailsCallback
import com.example.mydogcat.util.PetsCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.await

class PetRepository(private val catService: CatService, private val dogService: DogService) {

    fun findDogs(callback: PetsCallback, limit: Int) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val dogs = async(Dispatchers.IO) { dogService.findDogs(limit, hasBreeds = 1).execute() }
                callback.onSuccess(dogs.await().body()!!, DOGS)
            } catch (e: Exception) {
                callback.onFailure(e.message.toString())
            } finally {
                callback.onComplete()
            }
        }
    }

    fun findCats(callback: PetsCallback, limit: Int) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val cats = async(Dispatchers.IO) { catService.findCats(limit).execute() }
                callback.onSuccess(cats.await().body()!!, CATS)
            } catch (e: Exception) {
                callback.onFailure(e.message.toString())
            } finally {
                callback.onComplete()
            }
        }
    }


    fun findDogDetails(callback: PetDetailsCallback, pet: Pet){
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val dogDetails = async(Dispatchers.IO) {
                    dogService.findDogDetails(pet.id).await()
                }.await()
                callback.onSuccess(dogDetails)
            } catch (e: Exception) {
                callback.onFailure(e.message.toString())
            } finally {
                callback.onComplete()
            }
        }
    }
    fun findCatDetails(callback: PetDetailsCallback, pet: Pet){
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val catDetails = async(Dispatchers.IO) {
                    dogService.findDogDetails(pet.id).await()
                }.await()
                callback.onSuccess(catDetails)
            } catch (e: Exception) {
                callback.onFailure(e.message.toString())
            } finally {
                callback.onComplete()
            }
        }
    }

}