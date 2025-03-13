package com.example.api_tareas.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api_tareas.api.API
import com.example.api_tareas.api.model.usuario.UsuarioDTO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel encargado de gestionar la lista de usuarios en la aplicación.
 * Se comunica con la API para obtener la lista de usuarios disponibles.
 */
class UsuarioViewModel : ViewModel() {

    private val _usuarios = MutableStateFlow<List<UsuarioDTO>>(emptyList())
    val usuarios: StateFlow<List<UsuarioDTO>> = _usuarios

    /**
     * Obtiene la lista de usuarios desde la API y la almacena en [_usuarios].
     *
     * @param token Token de autenticación en formato Bearer.
     */
   fun obtenerUsuarios(token: String) {
        viewModelScope.launch {
            val response = API.retrofitService.getAllUsers("Bearer $token")
            if (response.isSuccessful) {
                _usuarios.value = response.body() ?: emptyList()
                Log.d("TAG", "Lista ")
            }else{
                Log.d("Error", "Error: ")
            }
        }
    }
}