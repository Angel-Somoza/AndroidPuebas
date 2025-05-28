package com.example.androidpuebas.Login.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.example.androidpuebas.ListRoutes.Activities.ListRouteActivity
import com.example.androidpuebas.Login.ViewModel.LoginState
import com.example.androidpuebas.Login.ViewModel.LoginViewModel
import com.example.androidpuebas.R
import com.example.androidpuebas.databinding.ActivityLoginBinding // Asumiendo que usas View Binding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupWindowInsets()
        setupObservers()
        setupClickListeners()
    }

    private fun setupWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupObservers() {
        loginViewModel.loginState.observe(this, Observer { state ->
            when (state) {
                is LoginState.Success -> {
                    val intent = Intent(this, ListRouteActivity::class.java)
                    startActivity(intent)
                    finish()
                    Toast.makeText(this, "Login exitoso", Toast.LENGTH_SHORT).show()
                }
                is LoginState.Error -> {
                    Toast.makeText(this, state.message, Toast.LENGTH_LONG).show()
                }
            }
        })



        loginViewModel.errorMessage.observe(this, Observer { error ->
            if (error.isNotEmpty()) {
                Toast.makeText(this, error, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun setupClickListeners() {
        binding.btnLogin.setOnClickListener {
            val usuario = binding.usuario.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            loginViewModel.login(usuario, password)
        }



    }




}