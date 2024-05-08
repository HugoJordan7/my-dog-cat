package com.example.mydogcat.di

import com.example.mydogcat.service.repository.DetailsRepository
import com.example.mydogcat.feature.details.viewModel.DetailsViewModel
import com.example.mydogcat.service.repository.MainRepository
import com.example.mydogcat.feature.main.viewModel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { providerMainViewModel(get()) }
    viewModel { providerDetailsViewModel(get()) }
}

private fun providerMainViewModel(repository: MainRepository) = MainViewModel(repository)

private fun providerDetailsViewModel(repository: DetailsRepository) = DetailsViewModel(repository)