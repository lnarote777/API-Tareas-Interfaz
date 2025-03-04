package com.example.api_tareas.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.auth0.jwt.JWT
import com.auth0.jwt.interfaces.DecodedJWT
import com.example.api_tareas.api.API
import com.example.api_tareas.api.model.usuario.UsuarioLoginDTO
import com.example.api_tareas.navigation.AppScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {
    private val _uiState = MutableStateFlow("")
    val uiState: StateFlow<String> = _uiState

    fun login(user: String, password: String, navController: NavController) {
        viewModelScope.launch {
            try {
                val response = API.retrofitService.login(UsuarioLoginDTO(user, password))
                if (response.isSuccessful) {
                    response.body()?.token?.let { token ->
                        _uiState.value = token
                        val userRol = obtenerRolDesdeToken(token)
                        navController.navigate(route = AppScreen.HomeScreen.route + "/$user/$userRol/$token")
                    } ?: run { _uiState.value = "Token no recibido" }
                } else {
                    _uiState.value = "Error de login: ${response.message()}"
                }
            } catch (e: Exception) {
                _uiState.value = "Error de conexión: ${e.message}"
            }
        }
    }

    fun obtenerRolDesdeToken(token: String): String? {
        return try {
            val decodedJWT: DecodedJWT = JWT.decode(token)
            val roles = decodedJWT.getClaim("roles").asString()
            roles?.let {
                if (it == "ROLE_ADMIN") "ADMIN" else "USER"
            } ?: "USER" //
        } catch (e: Exception) {
            "USER"
        }
    }
}