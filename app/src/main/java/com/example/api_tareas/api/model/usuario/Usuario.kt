package com.example.api_tareas.api.model.usuario

data class Usuario(
    val _id : String?, //email
    var nombre: String,
    var username: String,
    var password: String,
    var roles: String = "USER",
    var direccion: Direccion
)