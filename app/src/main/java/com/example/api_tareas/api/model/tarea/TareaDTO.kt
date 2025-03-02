package com.example.api_tareas.api.model.tarea

data class TareaDTO (
    val id : Int? = null,
    val titulo:String,
    val descripcion:String,
    val estado:String? = null,
    val username: String
)
