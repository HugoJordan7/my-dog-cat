package com.example.mydogcat.service.repository

import com.example.mydogcat.service.PetsRemoteDataSource
import com.example.mydogcat.util.PetsCallback

class MainRepository(private val dataSource: PetsRemoteDataSource) {
    fun findAllPets(callback: PetsCallback, limit: Int){
        dataSource.findAllPets(callback, limit)
    }
}