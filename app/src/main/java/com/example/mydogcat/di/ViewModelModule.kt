package com.example.mydogcat.di

import com.example.mydogcat.feature.details.viewModel.DetailsViewModel
import com.example.mydogcat.feature.main.viewModel.MainViewModel
import com.example.mydogcat.service.repository.PetRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { providerMainViewModel(get()) }
    viewModel { providerDetailsViewModel(get()) }
}

private fun providerMainViewModel(repository: PetRepository) = MainViewModel(repository)

private fun providerDetailsViewModel(repository: PetRepository) = DetailsViewModel(repository)