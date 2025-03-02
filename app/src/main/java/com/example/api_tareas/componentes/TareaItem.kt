package com.example.api_tareas.componentes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.api_tareas.api.model.tarea.TareaDTO

@Composable
fun TareaItem(
    tarea: TareaDTO,
    onCompleteClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = tarea.titulo)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = tarea.descripcion)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Estado: ${tarea.estado}")
            Spacer(modifier = Modifier.height(8.dp))

            Row {
                Button(onClick = onCompleteClick) {
                    Text("Completar")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = onDeleteClick) {
                    Text("Eliminar")
                }
            }
        }
    }
}