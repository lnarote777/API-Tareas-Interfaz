package com.example.api_tareas.componentes

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.api_tareas.R

/**
 * Muestra un cuadro de diálogo de confirmación para eliminar una tarea.
 *
 * Esta función muestra un cuadro de diálogo que pregunta al usuario si está seguro de eliminar una tarea.
 * El diálogo tiene dos botones:
 * - **Confirmar**: Ejecuta la acción de eliminar la tarea.
 * - **Cancelar**: Cierra el cuadro de diálogo sin hacer cambios.
 *
 * @param showAlert Indica si el cuadro de diálogo debe mostrarse o no.
 * @param onDismiss Función que se llama cuando el cuadro de diálogo se cierra sin tomar acción.
 * @param onConfirm Función que se llama cuando el usuario confirma la acción de eliminar la tarea.
 */
@Composable
fun DeleteSecure(
    showAlert: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
){
    if (showAlert) {
        AlertDialog(
            containerColor = Color.LightGray,
            onDismissRequest = { onDismiss() },
            text = { Text("Está seguro que desea eliminar esta tarea?\n Esta acción será permanente.") },
            confirmButton = {
                Button(
                    onClick = { onConfirm() },
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.boton))
                ) {
                    Text("Confirmar")
                }
            },
            dismissButton = {
                Button(
                    onClick = { onDismiss() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                ) {
                    Text("Cancelar")
                }
            }
        )
    }
}