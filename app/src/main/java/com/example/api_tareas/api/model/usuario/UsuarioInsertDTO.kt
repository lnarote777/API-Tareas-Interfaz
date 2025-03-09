package com.example.api_tareas.api.model.usuario

/**
 * Data Transfer Object (DTO) para la inserción de un nuevo usuario en la base de datos.
 *
 * @property username Nombre de usuario único.
 * @property nombre Nombre completo del usuario.
 * @property email Dirección de correo electrónico del usuario.
 * @property password Contraseña del usuario.
 * @property passwordRepeat Confirmación de la contraseña (debe coincidir con `password`).
 * @property rol Rol del usuario en el sistema (opcional).
 * @property calle Nombre de la calle de la dirección del usuario.
 * @property numero Número de la dirección del usuario.
 * @property municipio Municipio de la dirección del usuario.
 * @property provincia Provincia de la dirección del usuario.
 * @property cp Código postal de la dirección del usuario.
 */
data class UsuarioInsertDTO(
    val username: String,
    val nombre : String,
    val email: String,
    var password: String,
    val passwordRepeat: String,
    val rol: String?,
    val calle: String,
    val numero: String,
    val municipio: String,
    val provincia: String,
    val cp : String
)
