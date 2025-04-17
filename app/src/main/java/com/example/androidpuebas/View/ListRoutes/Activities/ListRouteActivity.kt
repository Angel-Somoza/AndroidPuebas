package com.example.androidpuebas.View.ListRoutes.Activities
import com.example.androidpuebas.databinding.ActivityListRouteBinding
import com.example.androidpuebas.databinding.ActivityLoginBinding
import com.example.androidpuebas.View.ListRoutes.ViewModel.ListRouteViewModel
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListRouteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListRouteBinding
    private val viewModel: ListRouteViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListRouteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Observar el mensaje del ViewModel
        viewModel.message.observe(this) { message ->
            binding.textView.text = message
        }

        // Botón para obtener el mensaje del servidor
        binding.button.setOnClickListener {
            viewModel.fetchMensaje()
        }

    }
}