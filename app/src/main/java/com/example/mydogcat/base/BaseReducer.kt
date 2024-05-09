package com.example.mydogcat.base

typealias Reducer<State,Event> = (state: State?, event: Event) -> State