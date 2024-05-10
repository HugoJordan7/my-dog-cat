package com.example.mydogcat.feature.main.reducer

import com.example.mydogcat.base.Reducer
import com.example.mydogcat.feature.main.event.MainEvent
import com.example.mydogcat.feature.main.event.MainState

class MainReducer: Reducer<MainState, MainEvent> {

    override fun invoke(state: MainState?, event: MainEvent): MainState = state?.let{
        when(event){
            is MainEvent.Loading -> state.copy(lastEvent = event,isProgress = true, isFailure = false)
            is MainEvent.ShowListCats -> state.copy(lastEvent = event, isProgress = false, cats = event.cats, isFailure = false)
            is MainEvent.ShowListDogs -> state.copy(lastEvent = event, isProgress = false, dogs = event.dogs, isFailure = false)
            is MainEvent.Error -> state.copy(lastEvent = event, isProgress = false, errorMessage = event.message, isFailure = true)
        }
    } ?: MainState()
}