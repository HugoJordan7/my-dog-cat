package com.example.mydogcat.di

import com.example.mydogcat.feature.details.reducer.DetailsReducer
import com.example.mydogcat.feature.main.reducer.MainReducer
import org.koin.dsl.module

val reducerModule = module {
    single { providerMainReducer() }
    single { providerDetailsReducer() }
}

private fun providerMainReducer() = MainReducer()
private fun providerDetailsReducer() = DetailsReducer()