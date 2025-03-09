package com.example.api_tareas.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.api_tareas.R
import com.example.api_tareas.componentes.TextFielPassword
import com.example.api_tareas.componentes.Textfield
import com.example.api_tareas.viewModel.RegistroViewModel

/**
 * Pantalla de registro para nuevos usuarios donde pueden ingresar sus datos personales, de contacto
 * y de dirección, y crear una cuenta.
 *
 * @param navController El controlador de navegación que permite navegar entre pantallas.
 * @param viewModel El ViewModel asociado a la pantalla de registro, que maneja el estado y las acciones de registro.
 */
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
    var errorMessage by remember { mutableStateOf("") }

    LaunchedEffect(uiState) {
        if (uiState.isNotEmpty()) {
            errorMessage = uiState
            nombre = ""
            username = ""
            password = ""
            email = ""
            municipio = ""
            passwordRepeat = ""
            provincia = ""
            calle = ""
            numero = ""
            cp = ""
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
                .padding(paddingValues)
        ) {
            Text("Regístrate", fontSize = 55.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(20.dp))
            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(top = 8.dp)
                )

            }

            Textfield(nombre, "Nombre") { nombre = it }
            Spacer(Modifier.height(20.dp))
            Textfield(email, "Email") { email = it }
            Spacer(Modifier.height(20.dp))
            Textfield(username, "Nombre de usuario") { username = it }
            Spacer(Modifier.height(20.dp))
            TextFielPassword(
                password = password,
                passVisible = passVisible,
                label = "Contraseña",
                valueChange = {password = it},
                onclick = {passVisible = !passVisible}
            )
            Spacer(Modifier.height(20.dp))
            TextFielPassword(password = passwordRepeat,
                passVisible = passReapeatVisible,
                label = "Repita la contraseña",
                valueChange = {passwordRepeat = it},
                onclick = {passReapeatVisible = !passReapeatVisible}
            )
            Spacer(Modifier.height(20.dp))
            Textfield(provincia, "Provincia") { provincia = it }
            Spacer(Modifier.height(20.dp))
            Textfield(municipio, "Municipio") { municipio = it }
            Textfield(calle, "Calle") { calle = it }
            Spacer(Modifier.height(20.dp))
            Textfield(numero, "Número") { numero = it }
            Spacer(Modifier.height(20.dp))
            Textfield(cp, "Código Postal") { cp = it }
            Spacer(Modifier.height(20.dp))

            Button(
                onClick = {
                    if (nombre.isBlank() || username.isBlank() || email.isBlank() || password.isBlank() ||
                        passwordRepeat.isBlank() || provincia.isBlank() || municipio.isBlank() || calle.isBlank() ||
                        numero.isBlank() || cp.isBlank()) {
                        errorMessage = "Por favor, complete todos los campos."
                    } else if (password != passwordRepeat) {
                        errorMessage = "Las contraseñas no coinciden."
                    } else {
                        viewModel.registerUser(
                            nombre = nombre,
                            username = username,
                            municipio = municipio.uppercase(),
                            provincia = provincia.uppercase(),
                            email = email,
                            cp = cp,
                            pass = password,
                            passRepeat = passwordRepeat,
                            calle = calle,
                            numero = numero,
                            navController = navController
                        )
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.boton)),
                modifier = Modifier.width(250.dp).height(50.dp)
            ) {
                Text("Registrarse", fontSize = 20.sp)
            }
        }
    }
}