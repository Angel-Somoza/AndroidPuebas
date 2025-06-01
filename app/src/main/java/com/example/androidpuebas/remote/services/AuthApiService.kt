package com.example.androidpuebas.remote.services

import com.example.androidpuebas.model.LoginRequest
import com.example.androidpuebas.model.LoginResponse
import retrofit2.Response  // Debería funcionar ahora
import retrofit2.http.Body
import retrofit2.http.POST


interface AuthApiService {
    @POST("api/login")// Es el post que se va a mandar al backend
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
    // el cuerpo login req, login res
}