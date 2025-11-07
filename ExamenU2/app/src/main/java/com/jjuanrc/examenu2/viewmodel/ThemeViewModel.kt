package com.jjuanrc.examenu2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.jjuanrc.examenu2.room.DataStore
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ThemeViewModel(application: Application) : AndroidViewModel(application) {

    private val ds = DataStore(application)

    val isDarkMode: StateFlow<Boolean> = ds.isDarkMode
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)

    fun toggleTheme(enabled: Boolean) {
        viewModelScope.launch {
            ds.setDarkMode(enabled)
        }
    }
}