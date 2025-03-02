package com.example.api_tareas.api.model.usuario

data class UsuarioDTO(
    val username: String,
    val nombre: String,
    val email: String?,
    val rol: String?
)
