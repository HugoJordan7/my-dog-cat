package com.example.mydogcat.feature.main.event

import com.example.mydogcat.model.Pet

sealed class MainEvent {
    object Loading : MainEvent()
    data class ShowListCats(val cats: List<Pet>) : MainEvent()
    data class ShowListDogs(val dogs: List<Pet>) : MainEvent()
    data class Error(val message: String) : MainEvent()
}

data class MainState(
    val lastEvent: MainEvent? = null,
    val cats: List<Pet> = emptyList(),
    val dogs: List<Pet> = emptyList(),
    val isProgress: Boolean = false,
    val errorMessage: String = "",
    val isFailure: Boolean = false
)