package com.example.api_tareas.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.api_tareas.componentes.TextFielPassword
import com.example.api_tareas.componentes.Textfield
import com.example.api_tareas.viewModel.RegistroViewModel
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(navController: NavController, viewModel: RegistroViewModel = viewModel() ){
    var nombre by rememberSaveable { mutableStateOf("") }
    var username by rememberSaveable { mutableStateOf("") }
    var provincia by rememberSaveable { mutableStateOf("") }
    var municipio by rememberSaveable { mutableStateOf("") }
    var calle by rememberSaveable { mutableStateOf("") }
    var numero by rememberSaveable { mutableStateOf("") }
    var cp by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordRepeat by rememberSaveable { mutableStateOf("") }
    var passVisible by rememberSaveable { mutableStateOf(false) }
    var passReapeatVisible by rememberSaveable { mutableStateOf(false) }

    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(uiState) {
        if (uiState.isNotEmpty()) {
            coroutineScope.launch {
                snackbarHostState.showSnackbar(uiState)
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Text("Regístrate", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Textfield(nombre, "Nombre") { nombre = it }
            Textfield(email, "Email") { email = it }
            Textfield(username, "Nombre de usuario") { username = it }
            TextFielPassword(
                password = password,
                passVisible = passVisible,
                label = "Contraseña",
                valueChange = {password = it},
                onclick = {passVisible = !passVisible}
            )
            TextFielPassword(password = passwordRepeat,
                passVisible = passReapeatVisible,
                label = "Repita la contraseña",
                valueChange = {passwordRepeat = it},
                onclick = {passReapeatVisible = !passReapeatVisible}
            )
            Textfield(provincia, "Provincia") { provincia = it }
            Textfield(municipio, "Municipio") { municipio = it }
            Textfield(calle, "Calle") { calle = it }
            Textfield(numero, "Número") { numero = it }
            Textfield(cp, "Código Postal") { cp = it }
            Spacer(Modifier.height(20.dp))

            Button(
                onClick = {
                    viewModel.registerUser(
                        nombre, username, municipio, provincia, email, cp, password, passwordRepeat, calle, numero, navController
                    )
                },
                modifier = Modifier.width(250.dp).height(50.dp)
            ) {
                Text("Registrarse", fontSize = 20.sp)
            }
        }
    }
}