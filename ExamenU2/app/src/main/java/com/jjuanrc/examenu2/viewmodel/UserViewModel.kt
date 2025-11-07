package com.jjuanrc.examenu2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.jjuanrc.examenu2.room.User
import com.jjuanrc.examenu2.room.UserRepo
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = UserRepo.getDatabase(application).userDao()
    val users: StateFlow<List<User>> = dao.getUsers()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addUser(name: String, age: Int) {
        viewModelScope.launch {
            dao.insertUser(User(name = name, age = age))
        }
    }
}