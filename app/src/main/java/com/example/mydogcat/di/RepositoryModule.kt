package com.example.mydogcat.di

import com.example.mydogcat.service.CatService
import com.example.mydogcat.service.DogService
import com.example.mydogcat.service.repository.PetRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { providerPetRepository(get(),get()) }
}

private fun providerPetRepository(catService: CatService, dogService: DogService) =
    PetRepository(catService, dogService)
