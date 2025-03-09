package com.example.api_tareas.api.model.usuario

/**
 * Clase que representa una dirección postal.
 *
 * @property calle Nombre de la calle.
 * @property num Número de la vivienda o local.
 * @property municipio Municipio al que pertenece la dirección.
 * @property provincia Provincia en la que se encuentra la dirección.
 * @property cp Código postal de la dirección.
 */
data class Direccion(
    val calle: String,
    val num: String,
    val municipio: String,
    val provincia: String,
    val cp: String
)
