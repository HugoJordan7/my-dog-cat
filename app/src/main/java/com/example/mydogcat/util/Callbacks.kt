package com.example.mydogcat.util

import com.example.mydogcat.model.Pet
import com.example.mydogcat.model.PetDetails

interface PetsCallback {
    fun onSuccess(pets: Pair<List<Pet>, List<Pet>>)
    fun onFailure(message: String)
    fun onComplete()
}

interface PetDetailsCallback {
    fun onSuccess(petDetails: PetDetails)
    fun onFailure(message: String)
    fun onComplete()
}
