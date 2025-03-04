package com.example.api_tareas.componentes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.api_tareas.R
import com.example.api_tareas.api.model.tarea.TareaDTO

@Composable
fun TareaItem(
    tarea: TareaDTO,
    onCompleteClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = colorResource(R.color.tarjeta)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = tarea.titulo,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = tarea.descripcion,
                fontSize = 20.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Estado: ${tarea.estado}",
                fontSize = 20.sp)
            Spacer(modifier = Modifier.height(8.dp))

            Row {
                Button(onClick = onCompleteClick,
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.boton))
                ) {
                    Text("Completar")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = onDeleteClick,
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.boton))
                ) {
                    Text("Eliminar")
                }
            }
        }
    }
}