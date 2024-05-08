package com.example.mydogcat.feature.details.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.mydogcat.model.Pet
import com.example.mydogcat.model.PetDetails
import com.example.mydogcat.service.repository.PetRepository
import com.example.mydogcat.util.PetDetailsCallback

class DetailsViewModel(private val repository: PetRepository): ViewModel(), PetDetailsCallback {

    private var _progressIsVisible = mutableStateOf(true)
    val progressIsVisible: State<Boolean> get() = _progressIsVisible


    private var _petDetailsState: MutableState<PetDetails?> = mutableStateOf(null)
    val petDetailsState: State<PetDetails?> get() = _petDetailsState

    private var _errorMessageState = mutableStateOf(Pair(false,""))
    val errorMessageState: State<Pair<Boolean,String>> get() = _errorMessageState


    fun findPetDetails(pet: Pet){
        repository.findPetDetails(this,pet)
    }

    override fun onSuccess(petDetails: PetDetails) {
        _petDetailsState.value = petDetails
    }

    override fun onFailure(message: String) {
        _errorMessageState.value = Pair(true,message)
    }

    override fun onComplete() {
        _progressIsVisible.value = false
    }
}