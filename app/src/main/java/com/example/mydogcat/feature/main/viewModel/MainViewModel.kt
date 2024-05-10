package com.example.mydogcat.feature.main.viewModel

import androidx.lifecycle.viewModelScope
import com.example.mydogcat.base.BaseViewModel
import com.example.mydogcat.feature.main.event.MainEvent
import com.example.mydogcat.feature.main.event.MainEvent.*
import com.example.mydogcat.feature.main.event.MainState
import com.example.mydogcat.feature.main.reducer.MainReducer
import com.example.mydogcat.model.Pet
import com.example.mydogcat.service.repository.PetRepository
import com.example.mydogcat.util.PetsCallback
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: PetRepository,
    reducer: MainReducer
) : BaseViewModel<MainState, MainEvent>(reducer) {

    private val limit = 20

    fun findAllPets() {
        updateState(Loading)
        findAllDogs()
        findAllCats()
    }

    private fun findAllDogs() {
        val callback = object : PetsCallback {
            override fun onSuccess(pets: List<Pet>) {
                val dogs = pets.filter { !it.url.contains(".gif") }
                updateState(ShowListDogs(dogs))
            }

            override fun onFailure(message: String) {
                updateState(Error(message))
            }
        }
        viewModelScope.launch {
            repository.findDogs(callback, limit)
        }
    }

    private fun findAllCats() {
        val callback = object : PetsCallback {
            override fun onSuccess(pets: List<Pet>) {
                val cats = pets.filter { !it.url.contains(".gif") }
                updateState(ShowListCats(cats))
            }

            override fun onFailure(message: String) {
                updateState(Error(message))
            }
        }
        viewModelScope.launch {
            repository.findCats(callback, limit)
        }
    }

    override fun setInitialState(): MainState = MainState()

}