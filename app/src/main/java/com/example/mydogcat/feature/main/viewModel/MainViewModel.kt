package com.example.mydogcat.feature.main.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mydogcat.base.BaseViewModel
import com.example.mydogcat.base.Reducer
import com.example.mydogcat.feature.main.event.MainEvent
import com.example.mydogcat.feature.main.event.MainState
import com.example.mydogcat.feature.main.reducer.MainReducer
import com.example.mydogcat.model.Pet
import com.example.mydogcat.service.repository.PetRepository
import com.example.mydogcat.util.PetsCallback
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: PetRepository,
    reducer: MainReducer
): BaseViewModel<MainState,MainEvent>(reducer) {

    private val limit = 20

    private val _catsState = mutableStateOf<List<Pet>>(emptyList())
    val catsState: State<List<Pet>> get() = _catsState

    private val _dogsState = mutableStateOf<List<Pet>>(emptyList())
    val dogsState: State<List<Pet>> get() = _dogsState

    private var _progressState = mutableStateOf(true)
    val progressState: State<Boolean> get() = _progressState

    private var _errorMessageState = mutableStateOf("")
    val errorMessageState: State<String> get() = _errorMessageState

    private var _isFailureState = mutableStateOf(false)
    val isFailureState: State<Boolean> get() = _isFailureState

    fun findAllPets() {
        findAllDogs()
        findAllCats()
    }

    private fun findAllDogs() {
        val callback = object : PetsCallback {
            override fun onSuccess(pets: List<Pet>) {
                _dogsState.value = pets.filter { pet ->
                    !pet.url.contains(".gif")
                }
            }
            override fun onFailure(message: String) {
                _isFailureState.value = true
                _errorMessageState.value = "onFailure Dogs"
            }
            override fun onComplete() {
                _progressState.value = false
            }
        }
        viewModelScope.launch {
            repository.findDogs(callback, limit)
        }
    }

    private fun findAllCats() {
        val callback = object : PetsCallback {
            override fun onSuccess(pets: List<Pet>) {
                _catsState.value = pets.filter { pet ->
                    !pet.url.contains(".gif")
                }
            }
            override fun onFailure(message: String) {
                _isFailureState.value = true
                _errorMessageState.value = "onFailure Cats"
            }
            override fun onComplete() {
                _progressState.value = false
            }
        }
        viewModelScope.launch {
            repository.findCats(callback, limit)
        }
    }

    override fun setInitialState(): MainState = MainState()

}