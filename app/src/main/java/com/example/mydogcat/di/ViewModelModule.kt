package com.example.mydogcat.di

import com.example.mydogcat.feature.details.reducer.DetailsReducer
import com.example.mydogcat.feature.details.viewModel.DetailsViewModel
import com.example.mydogcat.feature.main.reducer.MainReducer
import com.example.mydogcat.feature.main.viewModel.MainViewModel
import com.example.mydogcat.service.repository.PetRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { providerMainViewModel(get(),get()) }
    viewModel { providerDetailsViewModel(get(),get()) }
}

private fun providerMainViewModel(repository: PetRepository, reducer: MainReducer) =
    MainViewModel(repository,reducer)

private fun providerDetailsViewModel(repository: PetRepository, reducer: DetailsReducer) =
    DetailsViewModel(repository,reducer)