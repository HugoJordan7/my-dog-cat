package com.example.mydogcat.feature.details.reducer

import com.example.mydogcat.base.Reducer
import com.example.mydogcat.feature.details.event.DetailsEvent
import com.example.mydogcat.feature.details.event.DetailsState

class DetailsReducer: Reducer<DetailsState,DetailsEvent> {
    override fun invoke(state: DetailsState?, event: DetailsEvent): DetailsState {
        return state?.let {
            when(event){
                is DetailsEvent.Loading -> state.copy(lastEvent = event, isLoading = true, isFailure = false)
                is DetailsEvent.ShowPetDetails -> state.copy(lastEvent = event, isLoading = false, isFailure = false, petDetails = event.petDetails)
                is DetailsEvent.Error -> state.copy(lastEvent = event, isLoading = true, isFailure = false, errorMessage = event.message)
            }
        } ?: DetailsState()
    }

}