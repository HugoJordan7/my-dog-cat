package com.example.mydogcat.di

import com.example.mydogcat.features.details.data.DetailsRepository
import com.example.mydogcat.features.details.viewModel.DetailsViewModel
import com.example.mydogcat.features.main.data.MainRepository
import com.example.mydogcat.features.main.viewModel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { providerMainViewModel(get()) }
    viewModel { providerDetailsViewModel(get()) }
}

private fun providerMainViewModel(repository: MainRepository) = MainViewModel(repository)

private fun providerDetailsViewModel(repository: DetailsRepository) = DetailsViewModel(repository)