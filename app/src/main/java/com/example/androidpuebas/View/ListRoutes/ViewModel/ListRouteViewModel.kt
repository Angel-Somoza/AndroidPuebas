package com.example.androidpuebas.View.ListRoutes.ViewModel
import android.util.Log
import androidx.lifecycle.LiveData
import retrofit2.Call
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewModelScope
import com.example.androidpuebas.View.ListRoutes.Repository.ListRouteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListRouteViewModel @Inject constructor(private val repository: ListRouteRepository) : ViewModel() {
    private val _message = MutableLiveData<String>()
    val message: LiveData<String> get() = _message

    fun fetchMensaje() {

        viewModelScope.launch {
            try {
                val result =  (repository.GetMessage())
                _message.value = result.menssage

            } catch (e: Exception) {
                Log.e("UserViewModel", "Error fetching users: ${e.message}")
            }
        }
    }}