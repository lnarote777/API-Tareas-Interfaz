package com.example.api_tareas.navigation

/**
 * Clase sellada que define las pantallas principales de la aplicación y sus rutas asociadas.
 *
 * Cada objeto dentro de la clase representa una pantalla de la aplicación, y el valor de `route` es la ruta
 * que se utilizará para la navegación en el `NavHost`.
 *
 * Las pantallas definidas son:
 * - [PortadaScreen]: Pantalla de bienvenida al iniciar la aplicación.
 * - [LoginScreen]: Pantalla donde los usuarios pueden ingresar sus credenciales.
 * - [RegisterScreen]: Pantalla para que los usuarios se registren en la aplicación.
 * - [HomeScreen]: Pantalla principal donde se gestionan las tareas y se accede a otras funciones.
 * - [PerfilScreen]: Pantalla donde el usuario puede ver y modificar su perfil.
 */
sealed class AppScreen (val route: String) {

    object PortadaScreen: AppScreen("PortadaScreen")

    object LoginScreen: AppScreen("LoginScreen")

    object RegisterScreen: AppScreen("RegisterScreen")

    object HomeScreen: AppScreen("HomeScreen")

    object PerfilScreen: AppScreen("PerfilScreen")

}