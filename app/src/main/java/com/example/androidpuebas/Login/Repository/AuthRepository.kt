package com.example.androidpuebas.Login.Repository

import com.example.androidpuebas.model.LoginRequest
import com.example.androidpuebas.model.LoginResponse
import com.example.androidpuebas.remote.services.AuthApiService
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val authApiService: AuthApiService
) {
    suspend fun login(usuario: String, password: String): Response<LoginResponse> {// funcion que retorna el apiservice del login
        return authApiService.login(LoginRequest(usuario, password))
    }
}