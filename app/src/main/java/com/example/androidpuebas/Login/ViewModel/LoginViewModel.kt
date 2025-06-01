package com.example.androidpuebas.Login.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidpuebas.Login.Repository.AuthRepository
import com.example.androidpuebas.model.LoginState
import com.example.androidpuebas.model.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _loginState = MutableLiveData<LoginState>()
    val loginState: LiveData<LoginState> = _loginState// variables de estado de login


    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage// variables de error

    fun login(usuario: String, password: String) {
        viewModelScope.launch {
            try {
                _errorMessage.value = ""// el valor de error sera vacio

                if (usuario.isEmpty() || password.isBlank()) {// si los text son vacios
                    _errorMessage.value = "Usuario y contraseña son requeridos"
                }

                val response = authRepository.login(usuario, password)// buscamos el servicio con el repositorio

                if (response.isSuccessful) {// si el response es exitoso
                    val loginResponse = response.body() // almacenamos el vuerpo del response
                    if (loginResponse?.success == true) {// si es verdadero
                        _loginState.value = LoginState.Success(loginResponse.user!!)// almacenamos el valor del estado de login exitoso
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
            }
        }
    }
}

