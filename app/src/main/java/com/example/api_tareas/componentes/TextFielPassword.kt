package com.example.api_tareas.componentes

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.api_tareas.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFielPassword(password: String, passVisible: Boolean, label: String, valueChange: (String) -> Unit, onclick: () -> Unit){
    OutlinedTextField(
        value = password,
        onValueChange = valueChange,
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
            .height(60.dp),
        visualTransformation = if (passVisible) VisualTransformation.None else PasswordVisualTransformation(),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Blue,
            unfocusedBorderColor = Color.LightGray,
            cursorColor = Color.Blue
        ),
        singleLine = true,
        trailingIcon ={
            val image = if (passVisible) painterResource(R.drawable.visibilityoff) else painterResource(
                R.drawable.visibility)
            val description = if (passVisible) "Ocultar contraseña" else "Mostrar contraseña"
            IconButton(onClick = onclick) {
                Icon(
                    painter = image,
                    contentDescription = description,
                    tint = Color.Black,
                    modifier = Modifier.size(20.dp)
                )
            }
        },
        label = { Text(label) }
    )
}