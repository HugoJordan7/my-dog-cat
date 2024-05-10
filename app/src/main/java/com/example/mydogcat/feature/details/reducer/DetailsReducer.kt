package com.example.mydogcat.feature.details.reducer

import com.example.mydogcat.base.Reducer
import com.example.mydogcat.feature.details.event.DetailsEvent
import com.example.mydogcat.feature.details.event.DetailsState

class DetailsReducer: Reducer<DetailsState,DetailsEvent> {
    override fun invoke(state: DetailsState?, event: DetailsEvent): DetailsState {
        return state?.let {
            when(event){
                is DetailsEvent.Initial -> state.copy(lastEvent = event)
                is DetailsEvent.Final -> state.copy(lastEvent = event)
            }
        } ?: DetailsState()
    }

}