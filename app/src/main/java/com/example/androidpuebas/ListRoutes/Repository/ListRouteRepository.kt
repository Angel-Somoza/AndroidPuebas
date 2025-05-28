package com.example.androidpuebas.ListRoutes.Repository
import com.example.androidpuebas.remote.services.ApiServiceLH
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ListRouteRepository @Inject constructor(private val apiService: ApiServiceLH) {
    suspend  fun GetMessage() = apiService.getmessage()

}
