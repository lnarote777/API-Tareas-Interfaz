package com.example.api_tareas.api.model.tarea

import com.example.api_tareas.api.model.usuario.Usuario
import java.util.concurrent.atomic.AtomicInteger

data class Tarea(
    var _id: Int? = null,
    val titulo: String,
    val descripcion: String,
    var estado: String, // COMPLETADA/PENDIENTE
    val usuario: Usuario
){
    companion object {
        val cont = AtomicInteger(1)
    }
}