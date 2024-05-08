package com.example.mydogcat.feature.main.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.mydogcat.model.Pet
import com.example.mydogcat.service.repository.PetRepository
import com.example.mydogcat.util.PetsCallback

class MainViewModel(
    private val repository: PetRepository
): ViewModel(), PetsCallback {

    private val _catsState = mutableStateOf<List<Pet>>(emptyList())
    val catsState: State<List<Pet>> get() = _catsState

    private val _dogsState = mutableStateOf<List<Pet>>(emptyList())
    val dogsState: State<List<Pet>> get() = _dogsState

    private var _progressState = mutableStateOf(true)
    val progressState: State<Boolean> get() = _progressState

    private var _errorMessageState = mutableStateOf(Pair(false,""))
    val errorMessageState: State<Pair<Boolean,String>> get() = _errorMessageState

    fun findAllPets(limit: Int){
        repository.findAllPets(this, limit)
    }

    override fun onSuccess(pets: Pair<List<Pet>, List<Pet>>) {
        _dogsState.value = pets.first.filter { pet ->
            !pet.url.contains(".gif")
        }
        _catsState.value = pets.second.filter{ pet ->
            !pet.url.contains(".gif")
        }
    }

    override fun onFailure(message: String) {
        _errorMessageState.value = Pair(true,message)
    }

    override fun onComplete() {
        _progressState.value = false
    }
}