package com.example.mydogcat.feature.main.reducer

import com.example.mydogcat.base.Reducer
import com.example.mydogcat.feature.main.event.MainEvent
import com.example.mydogcat.feature.main.event.MainState

class MainReducer: Reducer<MainState, MainEvent> {

    override fun invoke(state: MainState?, event: MainEvent): MainState = state?.let{
        when(event){
            is MainEvent.Initial -> state.copy(lastEvent = event)
            is MainEvent.Final -> state.copy(lastEvent = event)
        }
    } ?: MainState()
}