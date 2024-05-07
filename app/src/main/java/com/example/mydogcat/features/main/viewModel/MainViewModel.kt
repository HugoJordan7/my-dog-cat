package com.example.mydogcat.features.main.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.mydogcat.features.main.data.MainRepository
import com.example.mydogcat.model.Pet

class MainViewModel(private val repository: MainRepository): ViewModel() {

    private val _catsState = mutableStateOf<List<Pet>>(emptyList())
    val catsState: State<List<Pet>>
        get() = _catsState

    private val _dogsState = mutableStateOf<List<Pet>>(emptyList())
    val dogsState: State<List<Pet>>
        get() = _dogsState
}