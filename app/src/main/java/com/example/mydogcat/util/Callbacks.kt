package com.example.mydogcat.util

import com.example.mydogcat.model.Pet
import com.example.mydogcat.model.PetDetails

interface PetsCallback {
    fun onSuccess(pets: List<Pet>)
    fun onFailure(message: String)
}

interface PetDetailsCallback {
    fun onSuccess(petDetails: PetDetails)
    fun onFailure(message: String)
}
