package com.example.androidpuebas.remote.services

import com.example.androidpuebas.model.User

import retrofit2.http.GET

interface ApiServiceLH {
    @GET("Date") // Ruta de API en el servidor local
    suspend fun  getmessage(): User
}
