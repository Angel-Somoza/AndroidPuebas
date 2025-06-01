package com.example.androidpuebas.model

sealed class LoginState {
    data class Success(val user: UserData) : LoginState()
    data class Error(val message: String) : LoginState()
}