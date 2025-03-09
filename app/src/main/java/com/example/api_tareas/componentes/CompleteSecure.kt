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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.api_tareas.R

/**
 * Muestra un cuadro de diálogo de confirmación para completar una tarea.
 *
 * Esta función muestra un cuadro de diálogo que pregunta al usuario si está seguro de completar una tarea.
 * El diálogo tiene dos botones:
 * - **Confirmar**: Ejecuta la acción de completar la tarea.
 * - **Cancelar**: Cierra el cuadro de diálogo sin hacer cambios.
 *
 * @param showAlert Indica si el cuadro de diálogo debe mostrarse o no.
 * @param onDismiss Función que se llama cuando el cuadro de diálogo se cierra sin tomar acción.
 * @param onConfirm Función que se llama cuando el usuario confirma la acción de completar la tarea.
 */
@Composable
fun CompleteSecure(
    showAlert: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
){
    if (showAlert) {
        AlertDialog(
            containerColor = Color.LightGray,
            onDismissRequest = { onDismiss() },
            text = { Text("Está seguro que desea completar esta tarea?\n Esta acción será permanente.") },
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