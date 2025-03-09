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

/**
 * ViewModel para gestionar el registro de un nuevo usuario.
 * Se encarga de realizar la solicitud de registro a la API y manejar el estado de la interfaz de usuario.
 */
class RegistroViewModel: ViewModel() {
    private val _uiState = MutableStateFlow("")
    val uiState: StateFlow<String> = _uiState

    /**
     * Registra un nuevo usuario en la aplicación.
     * Realiza la solicitud a la API con los datos del usuario y maneja el resultado del registro.
     * Si el registro es exitoso, navega a la pantalla de inicio de sesión. Si hay errores, actualiza el estado con el mensaje correspondiente.
     *
     * @param nombre El nombre completo del usuario.
     * @param username El nombre de usuario.
     * @param municipio El municipio del usuario.
     * @param provincia La provincia del usuario.
     * @param email El correo electrónico del usuario.
     * @param cp El código postal del usuario.
     * @param pass La contraseña proporcionada por el usuario.
     * @param passRepeat La confirmación de la contraseña.
     * @param calle La calle donde reside el usuario.
     * @param numero El número de la dirección del usuario.
     * @param navController El controlador de navegación que permite navegar a otras pantallas.
     */
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
                // Realiza la solicitud de registro a la API
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
                    _uiState.value = "Error al registrar usuario"
                }
            } catch (e: Exception) {
                _uiState.value = "Error de conexión: ${e.message}"
            }
        }

    }
}