package com.example.api_tareas.api.model.tarea

import com.example.api_tareas.api.model.usuario.Usuario
import java.util.concurrent.atomic.AtomicInteger

/**
 * Representa una tarea dentro del sistema de gestión de tareas.
 *
 * @property _id Identificador único de la tarea, generado automáticamente si no se proporciona.
 * @property titulo Título descriptivo de la tarea.
 * @property descripcion Descripción detallada de la tarea.
 * @property estado Estado actual de la tarea, puede ser "COMPLETADA" o "PENDIENTE".
 * @property usuario Usuario asociado a la tarea.
 */
data class Tarea(
    var _id: Int? = null,
    val titulo: String,
    val descripcion: String,
    var estado: String, // COMPLETADA/PENDIENTE
    val usuario: Usuario
){
    companion object {
        /**
         * Contador atómico para la generación de identificadores únicos de tareas.
         */
        val cont = AtomicInteger(1)
    }
}