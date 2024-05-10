package com.example.mydogcat.feature.details.event

import com.example.mydogcat.model.PetDetails

sealed class DetailsEvent{
    data object Loading: DetailsEvent()
    data class ShowPetDetails(val petDetails: PetDetails?): DetailsEvent()
    data class Error(val message: String): DetailsEvent()
}

data class DetailsState(
    val lastEvent: DetailsEvent? = null,
    val isLoading: Boolean = false,
    val petDetails: PetDetails? = null,
    val isFailure: Boolean = false,
    val errorMessage: String = ""
)