package com.example.androidpuebas.View.remote.Dagger
import com.example.androidpuebas.View.remote.services.ApiServiceLH
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteApiModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.1.36:3000/") // Dirección localhost para emulador
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Provides
    @Singleton
    fun provideApiServiceLh(retrofit: Retrofit): ApiServiceLH {
        return retrofit.create(ApiServiceLH::class.java)
    }
}
