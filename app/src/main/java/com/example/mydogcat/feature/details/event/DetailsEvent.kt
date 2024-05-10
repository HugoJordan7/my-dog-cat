package com.example.mydogcat.feature.details.event

sealed class DetailsEvent{
    object Initial: DetailsEvent()
    data class Final(val bool: Boolean): DetailsEvent()
}

data class DetailsState(
    val lastEvent: DetailsEvent? = null
)