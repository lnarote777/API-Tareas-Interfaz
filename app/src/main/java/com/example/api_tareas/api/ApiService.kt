package com.example.api_tareas.api

import com.example.api_tareas.api.model.tarea.Tarea
import com.example.api_tareas.api.model.tarea.TareaDTO
import com.example.api_tareas.api.model.usuario.LoginResponse
import com.example.api_tareas.api.model.usuario.UsuarioDTO
import com.example.api_tareas.api.model.usuario.UsuarioInsertDTO
import com.example.api_tareas.api.model.usuario.UsuarioLoginDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @POST("/usuarios/register")
    suspend fun insertUser(@Body usuarioInsertDTO: UsuarioInsertDTO): Response<UsuarioDTO>

    @POST("/usuarios/login")
    suspend fun login(@Body usuarioLoginDTO: UsuarioLoginDTO): Response<LoginResponse>

    @POST("/tareas/crear")
    suspend fun insertTarea(
        @Header("Authorization") token: String,
        @Body tareaDTO: TareaDTO): Response<TareaDTO>

    @GET("/tareas/listado-tareas")
    suspend fun getTareas(@Header("Authorization") token: String) : Response<List<TareaDTO>>

    @PUT("/tareas/update/{id}")
    suspend fun tareaCompleta(
        @Header("Authorization") token: String,
        @Path("id") id: Int): Response<TareaDTO>

    @DELETE("/tareas/delete/{id}")
    suspend fun tareaDelete(
        @Header("Authorization") token: String,
        @Path("id") id: Int): Response<Unit>
}