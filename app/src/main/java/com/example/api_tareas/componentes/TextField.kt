package com.example.api_tareas.componentes

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Composable que crea un campo de texto con un estilo de "OutlinedTextField".
 * Este componente permite al usuario ingresar y modificar texto.
 *
 * @param value El valor actual del campo de texto.
 * @param label La etiqueta que se muestra encima del campo de texto, indicando su propósito.
 * @param valueChange Función que se ejecuta cuando el valor del campo de texto cambia.
 * Recibe el nuevo valor como parámetro, permitiendo que se actualice el estado.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Textfield(value: String, label: String, valueChange: (String) -> Unit){

    OutlinedTextField(
        value = value,
        onValueChange = valueChange ,
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
            .height(60.dp),
        textStyle = TextStyle(fontSize = 16.sp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Blue,
            unfocusedBorderColor = Color.LightGray,
            cursorColor = Color.Blue
        ),
        label = { Text(label) }
    )
}