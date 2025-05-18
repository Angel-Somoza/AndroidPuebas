package com.example.androidpuebas.View.remote.services

import com.example.androidpuebas.View.model.User

import retrofit2.http.GET

interface ApiServiceLH {
    @GET("Date") // Ruta de API en el servidor local
    suspend fun  getmessage(): User
}
