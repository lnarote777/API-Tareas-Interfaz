package com.example.api_tareas.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.api_tareas.R
import com.example.api_tareas.componentes.TextFielPassword
import com.example.api_tareas.componentes.Textfield
import com.example.api_tareas.navigation.AppScreen
import com.example.api_tareas.viewModel.LoginViewModel

/**
 * Pantalla de inicio de sesión donde el usuario puede ingresar su nombre de usuario y contraseña.
 *
 * @param navController El controlador de navegación que permite navegar entre pantallas.
 * @param viewModel El ViewModel asociado a la pantalla de inicio de sesión.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel = viewModel()){
    var user by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passVisible by rememberSaveable { mutableStateOf(false) }
    val uiState by viewModel.uiState.collectAsState()
    var errorMessage by remember { mutableStateOf("") }

    LaunchedEffect(uiState) {
        if (uiState.isNotEmpty()) {
            errorMessage = uiState
            user = ""
            password = ""
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(R.color.portada))
                .padding(paddingValues)
        ) {
            Text("Inicia Sesión", fontSize = 55.sp, fontWeight = FontWeight.Bold)

            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(top = 8.dp)
                )

            }

            Spacer(Modifier.height(20.dp))

            Textfield(user, "Usuario") { user = it }
            Spacer(Modifier.height(20.dp))

            TextFielPassword(password, passVisible, "Contraseña", { password = it }) {
                passVisible = !passVisible
            }
            Text("¿Aun no estas registrado? Regístrate.",
                textAlign = TextAlign.Right,
                modifier =  Modifier.clickable { navController.navigate(route = AppScreen.RegisterScreen.route) })
            Spacer(Modifier.height(40.dp))

            Button(
                onClick = {
                    if (user.isBlank() || password.isBlank()) {
                        errorMessage = "Por favor, completa ambos campos"
                    } else {
                        // Si todo está bien, intentamos hacer login
                        viewModel.login(user, password, navController)
                        errorMessage = ""
                    }
                },
                modifier = Modifier.width(250.dp).height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.boton))
            ) { Text("Iniciar Sesión", fontSize = 20.sp) }
        }
    }
}