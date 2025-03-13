package com.example.api_tareas.api

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

/**
 * Interfaz que define los endpoints disponibles para interactuar con la API de tareas.
 *
 * Proporciona métodos para gestionar usuarios (registro, login) y tareas (crear, listar, actualizar y eliminar).
 */
interface ApiService {
    /**
     * Registra un nuevo usuario en la aplicación.
     *
     * @param usuarioInsertDTO Datos del usuario a registrar.
     * @return Respuesta con los datos del usuario registrado.
     */
    @POST("/usuarios/register")
    suspend fun insertUser(@Body usuarioInsertDTO: UsuarioInsertDTO): Response<UsuarioDTO>

    /**
     * Realiza el login de un usuario.
     *
     * @param usuarioLoginDTO Credenciales del usuario para iniciar sesión.
     * @return Respuesta con los datos de la sesión iniciada, incluyendo el token de autenticación.
     */
    @POST("/usuarios/login")
    suspend fun login(@Body usuarioLoginDTO: UsuarioLoginDTO): Response<LoginResponse>

    /**
     * Obtiene una lista de todos los usuarios.
     *
     * @param token Token de autorización del usuario.
     * @return Respuesta con la lista de usuarios.
     */
    @GET("/usuarios/lista-usuarios")
    suspend fun getAllUsers(@Header("Authorization") token: String):Response<List<UsuarioDTO>>

    /**
     * Crea una nueva tarea.
     *
     * @param token Token de autorización del usuario.
     * @param tareaDTO Datos de la tarea a crear.
     * @return Respuesta con los datos de la tarea creada.
     */
    @POST("/tareas/crear")
    suspend fun insertTarea(
        @Header("Authorization") token: String,
        @Body tareaDTO: TareaDTO): Response<TareaDTO>

    /**
     * Obtiene la lista de todas las tareas del usuario.
     *
     * @param token Token de autorización del usuario.
     * @return Respuesta con la lista de tareas del usuario.
     */
    @GET("/tareas/listado-tareas")
    suspend fun getTareas(@Header("Authorization") token: String) : Response<List<TareaDTO>>

    /**
     * Marca una tarea como completa.
     *
     * @param token Token de autorización del usuario.
     * @param id ID de la tarea a actualizar.
     * @return Respuesta con los datos de la tarea actualizada.
     */
    @PUT("/tareas/update/{id}")
    suspend fun tareaCompleta(
        @Header("Authorization") token: String,
        @Path("id") id: Int): Response<TareaDTO>

    /**
     * Elimina una tarea.
     *
     * @param token Token de autorización del usuario.
     * @param id ID de la tarea a eliminar.
     * @return Respuesta vacía indicando que la tarea fue eliminada correctamente.
     */
    @DELETE("/tareas/delete/{id}")
    suspend fun tareaDelete(
        @Header("Authorization") token: String,
        @Path("id") id: Int): Response<Unit>
}