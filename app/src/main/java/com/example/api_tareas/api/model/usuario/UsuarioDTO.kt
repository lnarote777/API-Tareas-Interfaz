package com.example.api_tareas.api.model.usuario

/**
 * Data Transfer Object (DTO) para representar un usuario en respuestas de la API.
 *
 * @property username Nombre de usuario del usuario.
 * @property nombre Nombre completo del usuario.
 * @property email Dirección de correo electrónico del usuario (opcional).
 * @property rol Rol del usuario en el sistema (opcional).
 */
data class UsuarioDTO(
    val username: String,
    val nombre: String,
    val email: String?,
    val rol: String?
)
