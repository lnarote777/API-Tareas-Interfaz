package com.example.api_tareas.api.model.tarea

/**
 * Data Transfer Object (DTO) para representar una tarea sin exponer la entidad completa.
 *
 * @property id Identificador único de la tarea (puede ser nulo si aún no se ha asignado).
 * @property titulo Título descriptivo de la tarea.
 * @property descripcion Descripción detallada de la tarea.
 * @property estado Estado de la tarea (puede ser nulo si no se ha definido). Ejemplo: "COMPLETADA" o "PENDIENTE".
 * @property username Nombre de usuario asociado a la tarea.
 */
data class TareaDTO (
    val id : Int? = null,
    val titulo:String,
    val descripcion:String,
    val estado:String? = null,
    val username: String
)
