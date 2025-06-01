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
import com.example.androidpuebas.Login.ViewModel.LoginViewModel
import com.example.androidpuebas.R
import com.example.androidpuebas.RegistrarActivity
import com.example.androidpuebas.databinding.ActivityLoginBinding // Asumiendo que usas View Binding
import com.example.androidpuebas.model.LoginState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()// variable de viewmodels

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)// rooteamos el binding

        setupObservers()//obervadores que tenemos en los view models
        setupClickListeners()// funcion de ls clicks del boton, para el login
        binding.register.setOnClickListener {// navegacion de login a register activity
            val intent = Intent(this, RegistrarActivity::class.java)
            startActivity(intent)
        }
    }



    private fun setupObservers() {
        loginViewModel.loginState.observe(this, Observer { state -> //obsevamos el valor del estado del login
            when (state) {// cuando el estado de login sea
                is LoginState.Success -> { // exitoso
                    val intent = Intent(this, ListRouteActivity::class.java)// naveagara a las list de los routes
                    startActivity(intent)
                    finish()// cerramos la login por seguridad y no poder regresar
                    Toast.makeText(this, "Login exitoso", Toast.LENGTH_SHORT).show()
                }
                is LoginState.Error -> {// cuando el estado del login se encuentre en error
                    Toast.makeText(this, state.message, Toast.LENGTH_LONG).show()// errores de los servidores
                }
            }
        })



        loginViewModel.errorMessage.observe(this, Observer { error -> //observamos el valor del error
            if (error.isNotEmpty()) {// validamos si el error no es vacio
                Toast.makeText(this, error, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun setupClickListeners() {// los clicks que tenemos en el login
        binding.btnLogin.setOnClickListener {
            val usuario = binding.usuario.text.toString().trim()//almacenamos el text en una variable
            val password = binding.etPassword.text.toString().trim()

            loginViewModel.login(usuario, password)// view model de login
        }



    }




}