package com.example.mydogcat.service

import com.example.mydogcat.model.Pet
import com.example.mydogcat.util.PetDetailsCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.await

class PetDetailsRemoteDataSource {
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