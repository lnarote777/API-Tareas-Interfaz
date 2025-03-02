package com.example.api_tareas.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.api_tareas.api.API
import com.example.api_tareas.api.model.usuario.UsuarioInsertDTO
import com.example.api_tareas.navigation.AppScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegistroViewModel: ViewModel() {
    private val _uiState = MutableStateFlow("")
    val uiState: StateFlow<String> = _uiState

    fun registerUser(
        nombre: String,
        username:String,
        municipio:String,
        provincia: String,
        email: String,
        cp: String,
        pass: String,
        passRepeat: String,
        calle:String,
        numero: String,
        navController: NavController,
    ){

        val registerRequest = UsuarioInsertDTO(
            username = username,
            nombre = nombre,
            email = email,
            cp = cp,
            provincia = provincia,
            municipio = municipio,
            password = pass,
            passwordRepeat = passRepeat,
            calle = calle ,
            numero = numero,
            rol = null
        )

        viewModelScope.launch {
            try {
                val response = API.retrofitService.insertUser(registerRequest)

                if (response.isSuccessful) {
                    val usuarioDTO = response.body()
                    if (usuarioDTO != null) {
                        _uiState.value = "Registro exitoso. Redirigiendo al login..."
                        navController.navigate(route = AppScreen.LoginScreen.route)
                    } else {
                        _uiState.value = "Error: No se recibió un usuario válido"
                    }
                } else {
                    _uiState.value = "Error al registrar usuario: ${response.message()}"
                }
            } catch (e: Exception) {
                _uiState.value = "Error de conexión: ${e.message}"
            }
        }

    }
}