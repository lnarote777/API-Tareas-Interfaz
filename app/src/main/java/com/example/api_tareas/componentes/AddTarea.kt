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

/**
 * Composable que muestra un cuadro de diálogo para agregar una nueva tarea.
 * Permite ingresar un título, descripción y, si el usuario es administrador, asignar la tarea a un usuario.
 *
 * @param showDialog Indica si el cuadro de diálogo debe ser visible o no.
 * @param onDismiss Función que se ejecuta cuando el cuadro de diálogo se cierra sin confirmar.
 * @param onConfirm Función que se ejecuta cuando el usuario confirma la creación de la tarea.
 * Se le pasan el título, descripción y el usuario asignado.
 * @param isAdmin Indica si el usuario tiene privilegios de administrador.
 * Si es `true`, se habilita el campo para asignar la tarea a un usuario.
 */
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
