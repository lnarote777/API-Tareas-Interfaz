package com.example.api_tareas.componentes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.api_tareas.R

@Composable
fun AddTarea(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onConfirm: (String, String, String) -> Unit,
    isAdmin: Boolean
) {
    var titulo by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var usuarioAsignado by remember { mutableStateOf("") }

    if (showDialog) {
        AlertDialog(
            containerColor = Color.LightGray,
            onDismissRequest = { onDismiss() },
            title = { Text(text = "Nueva Tarea") },
            text = {
                Column {
                    TextField(
                        value = titulo,
                        onValueChange = { titulo = it },
                        label = { Text("Título") }
                    )
                    Spacer(Modifier.height(8.dp))
                    TextField(
                        value = descripcion,
                        onValueChange = { descripcion = it },
                        label = { Text("Descripción") }
                    )
                    if (isAdmin) {
                        Spacer(Modifier.height(8.dp))
                        TextField(
                            value = usuarioAsignado,
                            onValueChange = { usuarioAsignado = it },
                            label = { Text("Asignar a (Usuario)") }
                        )
                    }
                }
            },
            confirmButton = {
                Button(onClick = { onConfirm(titulo, descripcion, usuarioAsignado) },
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.boton))
                ) {
                    Text("Agregar")
                }
            },
            dismissButton = {
                Button(onClick = { onDismiss() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                ) {
                    Text("Cancelar")
                }
            }
        )
    }
}
