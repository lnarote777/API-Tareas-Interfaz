package com.example.api_tareas.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.api_tareas.R
import com.example.api_tareas.api.model.tarea.TareaDTO
import com.example.api_tareas.componentes.AddTarea
import com.example.api_tareas.componentes.HeaderHome
import com.example.api_tareas.componentes.TareaItem
import com.example.api_tareas.viewModel.TareaViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavController, user: String?, token: String, viewModel: TareaViewModel = viewModel()){
    val tareas = viewModel.tareas
    val loading by viewModel.loading
    val error by viewModel.error
    var showAddDialog by remember { mutableStateOf(false) }

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(tareas) {
        viewModel.obtenerTareas(token)
        coroutineScope.launch {
            error?.let { snackbarHostState.showSnackbar(it) }
        }
    }

    Column(
        Modifier.fillMaxSize()
            .padding(top = 30.dp)
            .background(colorResource(R.color.portada))
    ) {
        if (user != null) {
            HeaderHome(navController, user)
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }

        if (tareas.value.isNotEmpty()){
            LazyColumn(modifier = Modifier.padding(16.dp)) {
                items(tareas.value.size) { id ->
                    val tarea = tareas.value[id]
                    TareaItem(
                        tarea = tarea,
                        onCompleteClick = { tarea.id?.let { id1 -> viewModel.completarTarea(id1, token) } },
                        onDeleteClick = { tarea.id?.let { id1 -> viewModel.eliminarTarea(id1, token) } }
                    )
                }
            }
        }else{
            Text(text = "Esto está un poco vacío...",
                textAlign = TextAlign.Center
            )
        }

        FloatingActionButton(
            onClick = { showAddDialog = true },
            modifier = Modifier.padding(16.dp).align(alignment = AbsoluteAlignment.Right)

        ) {
            Icon(Icons.Filled.Add, contentDescription = "Añadir tarea")
        }

        AddTarea(
            showDialog = showAddDialog,
            onDismiss = {showAddDialog = false},
            onConfirm = { titulo, descripcion ->
                user?.let {
                    TareaDTO(
                        titulo = titulo,
                        descripcion = descripcion,
                        username = it
                    )
                }?.let { viewModel.agregarTarea(it, token) }
                showAddDialog = false
            }
        )
    }
}
