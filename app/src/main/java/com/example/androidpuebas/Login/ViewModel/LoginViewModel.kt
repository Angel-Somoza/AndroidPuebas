package com.example.androidpuebas.Login.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidpuebas.Login.Repository.AuthRepository
import com.example.androidpuebas.model.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _loginState = MutableLiveData<LoginState>()
    val loginState: LiveData<LoginState> = _loginState

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun login(usuario: String, password: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _errorMessage.value = ""

                if (usuario.isBlank() || password.isBlank()) {
                    _errorMessage.value = "Usuario y contraseña son requeridos"
                    _isLoading.value = false
                    return@launch
                }

                val response = authRepository.login(usuario, password)

                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse?.success == true) {
                        _loginState.value = LoginState.Success(loginResponse.user!!)
                    } else {
                        _errorMessage.value = loginResponse?.message ?: "Error desconocido"
                        _loginState.value = LoginState.Error(loginResponse?.message ?: "Error desconocido")
                    }
                } else {
                    _errorMessage.value = "Error de conexión: ${response.code()}"
                    _loginState.value = LoginState.Error("Error de conexión: ${response.code()}")
                }

            } catch (e: Exception) {
                _errorMessage.value = "Error de red: ${e.message}"
                _loginState.value = LoginState.Error("Error de red: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun clearError() {
        _errorMessage.value = ""
    }
}

sealed class LoginState {
    data class Success(val user: UserData) : LoginState()
    data class Error(val message: String) : LoginState()
}