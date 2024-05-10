package com.example.mydogcat.feature.main.event

sealed class MainEvent{
    object Initial: MainEvent()
    data class Final(val bool: Boolean): MainEvent()
}

data class MainState(
    val lastEvent: MainEvent? = null
)