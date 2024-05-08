package com.example.mydogcat.di

import com.example.mydogcat.service.PetDetailsRemoteDataSource
import com.example.mydogcat.service.PetsRemoteDataSource
import org.koin.dsl.module

val remoteDataSourceModule = module {
    single { providerPetsRemoteDataSource() }
    single { providerPetDetailsRemoteDataSource() }
}

private fun providerPetsRemoteDataSource() = PetsRemoteDataSource()

private fun providerPetDetailsRemoteDataSource() = PetDetailsRemoteDataSource()