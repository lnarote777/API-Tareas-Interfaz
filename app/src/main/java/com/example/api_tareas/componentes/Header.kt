package com.example.api_tareas.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.api_tareas.R
import com.example.api_tareas.navigation.AppScreen

/**
 * Composable que muestra una barra superior con un icono de retroceso.
 * Permite al usuario navegar hacia atrás en la pila de navegación.
 *
 * @param navController Controlador de navegación utilizado para gestionar las acciones de navegación.
 * Se utiliza para volver a la pantalla anterior cuando el icono de retroceso es presionado.
 */
@Composable
fun Header(navController: NavController){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp)
            .background(color = colorResource(R.color.topbar))
            .height(70.dp)
    ) {
        Icon(imageVector = Icons.Default.ArrowBack,
            contentDescription = "Atrás",
            tint = Color.White,
            modifier = Modifier.clickable { navController.popBackStack() }
        )
    }
}
