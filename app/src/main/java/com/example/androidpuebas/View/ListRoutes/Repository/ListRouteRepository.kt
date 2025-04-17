package com.example.androidpuebas.View.ListRoutes.Repository
import com.example.androidpuebas.View.remote.services.ApiServiceLH
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ListRouteRepository @Inject constructor(private val apiService: ApiServiceLH) {
    suspend  fun GetMessage() = apiService.getmessage()

}
