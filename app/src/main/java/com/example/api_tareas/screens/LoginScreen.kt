package com.example.api_tareas.screens

import androidx.compose.foundation.background
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.api_tareas.R
import com.example.api_tareas.componentes.TextFielPassword
import com.example.api_tareas.componentes.Textfield
import com.example.api_tareas.viewModel.LoginViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel = viewModel()){
    var user by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passVisible by rememberSaveable { mutableStateOf(false) }
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
                .background(color = colorResource(R.color.portada))
                .padding(paddingValues)
        ) {
            Text("Inicia Sesión", fontSize = 55.sp, fontWeight = FontWeight.Bold)

            Spacer(Modifier.height(20.dp))

            Textfield(user, "Usuario") { user = it }
            Spacer(Modifier.height(20.dp))

            TextFielPassword(password, passVisible, "Contraseña", { password = it }) {
                passVisible = !passVisible
            }

            Spacer(Modifier.height(40.dp))

            Button(
                onClick = { viewModel.login(user, password, navController) },
                modifier = Modifier.width(250.dp).height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.boton))
            ) { Text("Iniciar Sesión", fontSize = 20.sp) }
        }
    }
}