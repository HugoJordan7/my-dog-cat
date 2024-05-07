package com.example.mydogcat.di

import com.example.mydogcat.features.details.data.DetailsRepository
import com.example.mydogcat.features.main.data.MainRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { providerMainRepository() }
    single { providerDetailsRepository() }
}

private fun providerMainRepository() = MainRepository()

private fun providerDetailsRepository() = DetailsRepository()