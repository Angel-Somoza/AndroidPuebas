package com.example.androidpuebas.model

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("usuario")
    val usuario: String,
    @SerializedName("password")
    val password: String
)