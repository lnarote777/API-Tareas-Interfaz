package com.example.api_tareas.api.model.usuario

/**
 * Representa la respuesta de una autenticación exitosa.
 *
 * @property token Token JWT generado tras el inicio de sesión exitoso.
 */
data class LoginResponse(val token: String)
