package com.jjuanrc.counterviewmodel.viewModels

import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {
    private val _counter = mutableIntStateOf(0)
    val counter = _counter

    fun add(){
        _counter.value++
    }
}