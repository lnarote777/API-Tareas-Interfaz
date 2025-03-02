package com.example.api_tareas.navigation

sealed class AppScreen (val route: String) {

    object PortadaScreen: AppScreen("PortadaScreen")

    object LoginScreen: AppScreen("LoginScreen")

    object RegisterScreen: AppScreen("RegisterScreen")

    object HomeScreen: AppScreen("HomeScreen")

    object PerfilScreen: AppScreen("PerfilScreen")

}