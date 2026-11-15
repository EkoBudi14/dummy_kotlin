package com.example.myapplication.ui

import com.example.myapplication.data.User

sealed class UiState {

    data object OnLoading : UiState()
    data class OnSuccess(val users : List<User>) : UiState()
    data class OnError(val message : String) : UiState()
}