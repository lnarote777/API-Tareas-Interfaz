package com.example.api_tareas.api.model.usuario

/**
 * Data Transfer Object (DTO) para el inicio de sesión de un usuario.
 *
 * @property username Nombre de usuario utilizado para la autenticación.
 * @property password Contraseña del usuario.
 */
data class UsuarioLoginDTO(
    val username: String,
    val password: String
)
