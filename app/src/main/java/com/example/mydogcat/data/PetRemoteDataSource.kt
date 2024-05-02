package com.example.mydogcat.data

import com.example.mydogcat.model.Pet
import com.example.mydogcat.util.PetDetailsCallback
import com.example.mydogcat.util.PetsCallback
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.await

class PetRemoteDataSource {

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

    fun findPetDetails(callback: PetDetailsCallback, pet: Pet){
        val retrofit = if (pet.url.contains("thedogapi")){
            HTTPData.dogRetrofit().create(PetAPI::class.java)
        } else{
            HTTPData.catRetrofit().create(PetAPI::class.java)
        }

        GlobalScope.launch(Dispatchers.Main) {
            try {
                val petDetails = async(Dispatchers.IO) {
                    retrofit.findPetDetails(pet.id).await()
                }.await()
                callback.onSuccess(petDetails)
            } catch (e: Exception) {
                callback.onFailure(e.message.toString())
            } finally {
                callback.onComplete()
            }
        }
    }

}