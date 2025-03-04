package com.example.api_tareas.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.api_tareas.R
import com.example.api_tareas.navigation.AppScreen
import kotlinx.coroutines.delay

@Composable
fun PortadaScreen(navController: NavController){
    var isLoading by remember { mutableStateOf(false) }
    var navigateTo by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(navigateTo) {
        navigateTo?.let {
            isLoading = true
            delay(1500)
            isLoading = false
            navController.navigate(it) {
                popUpTo(AppScreen.PortadaScreen.route) { inclusive = true }
            }
            navigateTo = null
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.portada)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "Logo",
                Modifier.size(400.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))

            if (isLoading) {
                CircularProgressIndicator(color = colorResource(R.color.boton))
            } else {
                Button(
                    onClick = { navigateTo = AppScreen.LoginScreen.route },
                    modifier = Modifier.width(250.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.boton)
                    )
                ) {
                    Text("Iniciar Sesión", fontSize = 18.sp)
                }

                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = { navigateTo = AppScreen.RegisterScreen.route },
                    modifier = Modifier.width(250.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor =  colorResource(R.color.boton)
                    )
                ) {
                    Text("Registrarse", fontSize = 18.sp)
                }
            }
        }
    }
}