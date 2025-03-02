package com.example.api_tareas.viewModel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api_tareas.api.API
import com.example.api_tareas.api.model.tarea.TareaDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TareaViewModel: ViewModel() {
    private val _tareas = mutableStateOf<List<TareaDTO>>(emptyList())
    val tareas: State<List<TareaDTO>> = _tareas

    private val _loading = mutableStateOf(false)
    val loading: State<Boolean> = _loading

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> = _error


    fun obtenerTareas(token: String) {
            viewModelScope.launch {
                _loading.value = true
                try {
                    val response = API.retrofitService.getTareas("Bearer $token")
                    if (response.isSuccessful) {
                        val tasks = response.body() ?: emptyList()
                        _tareas.value = tasks // Asignar tareas solo si no estaban cargadas
                    } else {
                        _error.value = "Error al obtener las tareas."
                    }
                } catch (e: Exception) {
                    _error.value = "Error al obtener las tareas: ${e.message}"
                } finally {
                    _loading.value = false
                }
            }

    }


    fun agregarTarea(tareaDTO: TareaDTO, token: String) {
        viewModelScope.launch {
            try {
                val response = API.retrofitService.insertTarea("Bearer $token", tareaDTO)
                if (response.isSuccessful) {
                   response.body()?.let {
                       _tareas.value += it
                   }
                } else {
                    _error.value = "Error al agregar la tarea."
                }
            } catch (e: Exception) {
                _error.value = "Error al agregar la tarea: ${e.message}"
            }
        }
    }

    fun completarTarea(id: Int, token: String) {
        viewModelScope.launch {
            try {
                val response = API.retrofitService.tareaCompleta("Bearer $token", id)
                if (response.isSuccessful) {
                    _tareas.value = _tareas.value.map {
                        if (it.id == id) it.copy(estado = "COMPLETADA") else it
                    }
                } else {
                    _error.value = "Error al completar la tarea."
                }
            } catch (e: Exception) {
                _error.value = "Error al completar la tarea: ${e.message}"
            }
        }
    }

    // Función para eliminar una tarea
    fun eliminarTarea(id: Int, token: String) {
        viewModelScope.launch {
            try {
                API.retrofitService.tareaDelete("Bearer $token", id)
                _tareas.value = _tareas.value.filter { it.id != id }
            } catch (e: Exception) {
                _error.value = "Error al eliminar la tarea: ${e.message}"
            }
        }
    }
}
