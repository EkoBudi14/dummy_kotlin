package com.example.myapplication.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.ApiCall
import com.example.myapplication.data.AppDatabase
import com.example.myapplication.data.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserViewModel(
    application: Application
) : AndroidViewModel(application) {


    private val userRepository = UserRepository(
        AppDatabase.getInstance(application).userDao(),
        ApiCall.userGet,
    )

    private val _state = MutableStateFlow<UiState>(UiState.OnLoading)
    val state = _state.asStateFlow()


    init {
        loadUser()
    }

    fun loadUser() {
        viewModelScope.launch {
            _state.value = UiState.OnLoading
            userRepository.getUser()
                .onSuccess { _state.value = UiState.OnSuccess(it) }
                .onFailure {  _state.value = UiState.OnError(it.message ?: "Error get user") }
        }
    }
}