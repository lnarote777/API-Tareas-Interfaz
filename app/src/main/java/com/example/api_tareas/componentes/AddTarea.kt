package com.example.api_tareas.componentes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.api_tareas.R
import com.example.api_tareas.viewModel.UsuarioViewModel

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
 * @param token Token de autenticación.
 */
@Composable
fun AddTarea(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onConfirm: (String, String, String) -> Unit,
    isAdmin: Boolean,
    viewModel: UsuarioViewModel = viewModel(),
    token: String
) {
    var titulo by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var selectedUser by remember { mutableStateOf<String?>(null) }

    val usuarios by viewModel.usuarios.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.obtenerUsuarios(token)
    }

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
                        Text("Asignar a (Usuario)")
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Button(onClick = { expanded = true }) {
                                Text(selectedUser ?: "Seleccionar usuario")
                            }
                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false }) {
                                usuarios.forEach { usuario ->
                                    DropdownMenuItem(
                                        text = { Text(usuario.username) },
                                        onClick = {
                                            selectedUser = usuario.username
                                            expanded = false
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            },
            confirmButton = {
                Button(onClick = { onConfirm(titulo, descripcion, selectedUser ?: "") },
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
