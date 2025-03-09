package com.example.api_tareas.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.api_tareas.screens.HomeScreen
import com.example.api_tareas.screens.PortadaScreen
import com.example.api_tareas.screens.RegisterScreen
import com.example.api_tareas.screens.LoginScreen
import com.example.api_tareas.screens.PerfilScreen
import com.example.api_tareas.viewModel.TareaViewModel


/**
 * Configura la navegación de la aplicación utilizando el componente `NavHost` de Jetpack Compose.
 * Define las rutas para las pantallas de la aplicación y los argumentos necesarios para las transiciones entre ellas.
 *
 * El `NavHost` es el contenedor de navegación que gestiona las transiciones entre las pantallas. El `navController`
 * es el encargado de manejar el flujo de navegación.
 *
 * @see PortadaScreen Pantalla inicial con bienvenida.
 * @see LoginScreen Pantalla para el inicio de sesión de usuarios.
 * @see RegisterScreen Pantalla para el registro de nuevos usuarios.
 * @see HomeScreen Pantalla principal, accesible después del login.
 * @see PerfilScreen Pantalla donde el usuario puede ver y modificar su perfil.
 */
@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreen.PortadaScreen.route){
        composable(AppScreen.PortadaScreen.route){
            PortadaScreen(navController)
        }

        composable(AppScreen.LoginScreen.route){
            LoginScreen(navController)
        }

        composable(AppScreen.RegisterScreen.route){
            RegisterScreen(navController)
        }

        composable(AppScreen.HomeScreen.route + "/{user}/{userRol}/{token}",
            arguments = listOf(
                navArgument(name = "user"){
                type = NavType.StringType
                },
                navArgument(name = "userRol"){
                type = NavType.StringType
                },
                navArgument(name = "token") {
                type = NavType.StringType
                }
            )
        ){
            HomeScreen(
                navController = navController,
                user = it.arguments?.getString("user"),
                token = it.arguments?.getString("token") ?: "",
                userRol = it.arguments?.getString("userRol")
            )
        }

        composable(AppScreen.PerfilScreen.route + "/{user}",
            arguments = listOf(navArgument(name = "user"){
                type = NavType.StringType
            })
        ) {
            PerfilScreen(
                navController,
                user = it.arguments?.getString("user")
            )
        }

    }
}
