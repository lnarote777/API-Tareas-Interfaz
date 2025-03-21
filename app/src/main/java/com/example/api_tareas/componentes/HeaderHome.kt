package com.example.api_tareas.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
 * Composable que muestra una barra superior con un icono de usuario.
 * Al hacer clic en el icono, navega a la pantalla de perfil del usuario.
 *
 * @param navController Controlador de navegación utilizado para gestionar las acciones de navegación.
 * Se utiliza para navegar a la pantalla de perfil del usuario cuando se hace clic en el icono de usuario.
 * @param user Nombre del usuario que se pasa a la ruta de la pantalla de perfil.
 * Puede ser `null`, en cuyo caso no se navegaría a la pantalla de perfil.
 */
@Composable
fun HeaderHome(navController: NavController, user: String?){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colorResource(R.color.topbar))
            .height(70.dp)
    ) {
        Spacer(Modifier.weight(1f))

        Icon(imageVector = Icons.Default.AccountCircle,
            contentDescription = "Usuario",
            tint = Color.White,
            modifier = Modifier
                .padding(10.dp)
                .size(50.dp)
                .clickable { navController.navigate(route = AppScreen.PerfilScreen.route + "/$user") }
        )
    }
}
