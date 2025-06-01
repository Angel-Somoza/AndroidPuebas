package com.example.androidpuebas.remote.Dagger
import com.example.androidpuebas.remote.services.ApiServiceLH
import com.example.androidpuebas.remote.services.AuthApiService
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
            .baseUrl("http://192.168.97.164:3000/")// cambiar la ip si es necesario o se cambia
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Provides
    @Singleton
    fun provideApiServiceLh(retrofit: Retrofit): ApiServiceLH {
        return retrofit.create(ApiServiceLH::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthApiService(retrofit: Retrofit): AuthApiService {// returnamos el api services del login
        return retrofit.create(AuthApiService::class.java)
    }
}
