package com.example.api_tareas.api.model.usuario

/**
 * Representa un usuario en el sistema.
 *
 * @property _id Identificador único del usuario (correo electrónico).
 * @property nombre Nombre completo del usuario.
 * @property username Nombre de usuario utilizado para autenticación.
 * @property password Contraseña del usuario (debe almacenarse de forma segura).
 * @property roles Rol o roles del usuario en el sistema (por defecto, "USER").
 * @property direccion Dirección del usuario.
 */
data class Usuario(
    val _id : String?, //email
    var nombre: String,
    var username: String,
    var password: String,
    var roles: String = "USER",
    var direccion: Direccion
)