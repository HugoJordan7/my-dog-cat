package com.example.mydogcat.service

import com.example.mydogcat.util.PetsCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class PetsRemoteDataSource {
    fun findAllPets(callback: PetsCallback, limit: Int) {
        val dogRetrofit = HTTPData.dogRetrofit().create(PetAPI::class.java)
        val catRetrofit = HTTPData.catRetrofit().create(PetAPI::class.java)
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val cats = async(Dispatchers.IO) { catRetrofit.findPetList(limit).execute() }
                val dogs = async(Dispatchers.IO) { dogRetrofit.findPetList(limit, hasBreeds = 1).execute() }
                callback.onSuccess(
                    Pair(dogs.await().body()!! , cats.await().body()!!)
                )
            } catch (e: Exception) {
                callback.onFailure(e.message.toString())
            } finally {
                callback.onComplete()
            }
        }
    }
}