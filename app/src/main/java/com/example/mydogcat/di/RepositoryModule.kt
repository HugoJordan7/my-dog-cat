package com.example.mydogcat.di

import com.example.mydogcat.service.PetDetailsRemoteDataSource
import com.example.mydogcat.service.PetsRemoteDataSource
import com.example.mydogcat.service.repository.DetailsRepository
import com.example.mydogcat.service.repository.MainRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { providerMainRepository(get()) }
    single { providerDetailsRepository(get()) }
}

private fun providerMainRepository(dataSource: PetsRemoteDataSource) = MainRepository(dataSource)

private fun providerDetailsRepository(dataSource: PetDetailsRemoteDataSource) = DetailsRepository(dataSource)