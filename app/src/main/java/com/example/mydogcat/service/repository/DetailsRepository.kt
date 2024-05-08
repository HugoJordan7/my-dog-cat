package com.example.mydogcat.service.repository

import com.example.mydogcat.model.Pet
import com.example.mydogcat.service.PetDetailsRemoteDataSource
import com.example.mydogcat.util.PetDetailsCallback

class DetailsRepository(private val dataSource: PetDetailsRemoteDataSource) {
    fun findPetDetails(callback: PetDetailsCallback, pet: Pet){
        dataSource.findPetDetails(callback, pet)
    }
}