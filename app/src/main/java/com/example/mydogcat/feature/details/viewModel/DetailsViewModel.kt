package com.example.mydogcat.feature.details.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mydogcat.feature.details.reducer.DetailsReducer
import com.example.mydogcat.model.Pet
import com.example.mydogcat.model.PetDetails
import com.example.mydogcat.service.repository.PetRepository
import com.example.mydogcat.util.PetDetailsCallback
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val repository: PetRepository,
    reducer: DetailsReducer
): ViewModel() {

    private var _progressIsVisible = mutableStateOf(true)
    val progressIsVisible: State<Boolean> get() = _progressIsVisible

    private var _petDetailsState: MutableState<PetDetails?> = mutableStateOf(null)
    val petDetailsState: State<PetDetails?> get() = _petDetailsState

    private var _errorMessageState = mutableStateOf("")
    val errorMessageState: State<String> get() = _errorMessageState

    private var _isFailureState = mutableStateOf(false)
    val isFailureState: State<Boolean> get() = _isFailureState

    fun findPetDetails(pet: Pet){
        val callback = object : PetDetailsCallback {
            override fun onSuccess(petDetails: PetDetails) {
                _petDetailsState.value = petDetails
            }
            override fun onFailure(message: String) {
                _errorMessageState.value = message
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

}