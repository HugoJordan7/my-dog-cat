package com.example.mydogcat.di

import com.example.mydogcat.service.PetService
import com.example.mydogcat.service.repository.PetRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { providerPetRepository(get()) }
}

private fun providerPetRepository(service: PetService) = PetRepository(service)
