package com.example.mydogcat.service.repository

import com.example.mydogcat.model.Pet
import com.example.mydogcat.service.PetService
import com.example.mydogcat.util.PetDetailsCallback
import com.example.mydogcat.util.PetsCallback

class PetRepository(
    private val service: PetService
) {

    fun findAllPets(callback: PetsCallback, limit: Int){
        service.findAllPets(callback, limit)
    }

    fun findPetDetails(callback: PetDetailsCallback, pet: Pet){
        service.findPetDetails(callback, pet)
    }
}