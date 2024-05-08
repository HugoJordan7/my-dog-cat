package com.example.mydogcat.di

import com.example.mydogcat.service.PetService
import org.koin.dsl.module

val serviceModule = module {
    single { providerPetService() }
}

private fun providerPetService() = PetService()
