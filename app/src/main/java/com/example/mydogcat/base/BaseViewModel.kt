package com.example.mydogcat.base

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<State, Event>(
    private val reducer: Reducer<State, Event>
) : ViewModel() {

    val state = mutableStateOf(value = setInitialState())

    val currentState: State? get() = state.value

    abstract fun setInitialState(): State

    fun updateState(newEvent: Event) {
        state.value = reducer(currentState, newEvent)
    }
}