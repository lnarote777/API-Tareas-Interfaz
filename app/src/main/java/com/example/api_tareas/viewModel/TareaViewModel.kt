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

/**
 * ViewModel para gestionar las tareas en la aplicación.
 * Maneja las operaciones relacionadas con la obtención, adición, eliminación y marcado de tareas como completadas.
 */
class TareaViewModel: ViewModel() {
    private val _tareas = mutableStateOf<List<TareaDTO>>(emptyList())
    val tareas: State<List<TareaDTO>> = _tareas

    private val _loading = mutableStateOf(false)
    val loading: State<Boolean> = _loading

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> = _error


    /**
     * Obtiene las tareas desde la API.
     * Realiza una solicitud a la API para obtener la lista de tareas.
     * En caso de éxito, actualiza la lista de tareas en el estado. Si ocurre un error, se guarda el mensaje de error.
     *
     * @param token El token de autorización que se usa para autenticar la solicitud.
     */
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


    /**
     * Agrega una nueva tarea a la lista.
     * Realiza una solicitud a la API para agregar una tarea y, si es exitosa, la agrega a la lista de tareas.
     *
     * @param tareaDTO El objeto `TareaDTO` que contiene los datos de la nueva tarea.
     * @param token El token de autorización que se usa para autenticar la solicitud.
     */
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

    /**
     * Marca una tarea como completada.
     * Realiza una solicitud a la API para actualizar el estado de la tarea a "COMPLETADA".
     *
     * @param id El ID de la tarea que se marcará como completada.
     * @param token El token de autorización que se usa para autenticar la solicitud.
     */
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

    /**
     * Elimina una tarea de la lista.
     * Realiza una solicitud a la API para eliminar una tarea y actualiza la lista de tareas eliminando la tarea correspondiente.
     *
     * @param id El ID de la tarea que se eliminará.
     * @param token El token de autorización que se usa para autenticar la solicitud.
     */
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
