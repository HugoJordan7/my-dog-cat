package com.example.mydogcat.feature.details.viewModel

import androidx.lifecycle.viewModelScope
import com.example.mydogcat.base.BaseViewModel
import com.example.mydogcat.feature.details.event.DetailsEvent
import com.example.mydogcat.feature.details.event.DetailsEvent.*
import com.example.mydogcat.feature.details.event.DetailsState
import com.example.mydogcat.feature.details.reducer.DetailsReducer
import com.example.mydogcat.model.Pet
import com.example.mydogcat.model.PetDetails
import com.example.mydogcat.service.repository.PetRepository
import com.example.mydogcat.util.PetDetailsCallback
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val repository: PetRepository,
    reducer: DetailsReducer
): BaseViewModel<DetailsState,DetailsEvent>(reducer) {

    fun findPetDetails(pet: Pet){
        updateState(Loading)
        val callback = object : PetDetailsCallback {
            override fun onSuccess(petDetails: PetDetails) {
                updateState(ShowPetDetails(petDetails))
            }
            override fun onFailure(message: String) {
                updateState(Error(message))
            }
        }
        viewModelScope.launch {
            if(pet.url.contains("thedogapi")){
                repository.findDogDetails(callback,pet)
            } else{
                repository.findCatDetails(callback,pet)
            }
        }
    }

    override fun setInitialState(): DetailsState = DetailsState()

}