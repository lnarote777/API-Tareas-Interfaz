package com.example.api_tareas.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.api_tareas.R
import com.example.api_tareas.api.model.tarea.TareaDTO
import com.example.api_tareas.componentes.AddTarea
import com.example.api_tareas.componentes.CompleteSecure
import com.example.api_tareas.componentes.DeleteSecure
import com.example.api_tareas.componentes.HeaderHome
import com.example.api_tareas.componentes.TareaItem
import com.example.api_tareas.viewModel.TareaViewModel
import kotlinx.coroutines.launch

/**
 * Pantalla principal de la aplicación donde se gestionan las tareas.
 *
 * En esta pantalla, los usuarios pueden ver una lista de tareas, agregar nuevas tareas,
 * completar tareas existentes y eliminar tareas. La pantalla también muestra un
 * indicador de carga mientras se obtienen las tareas desde el backend.
 *
 * @param navController Controlador de navegación utilizado para navegar entre pantallas.
 * @param user Nombre del usuario actual. Si es `null`, no se mostrará el encabezado de usuario.
 * @param userRol Rol del usuario actual (por ejemplo, "ADMIN" o "USER"). Este rol es utilizado para determinar
 * si el usuario tiene permisos especiales para asignar tareas.
 * @param token Token de autenticación utilizado para realizar solicitudes seguras a la API.
 * @param viewModel El `ViewModel` que gestiona la lógica y estado de las tareas. Es utilizado para obtener,
 * agregar, completar y eliminar tareas.
 */
@Composable
fun HomeScreen(navController: NavController, user: String?, userRol: String?, token: String, viewModel: TareaViewModel = viewModel()){
    val tareas = viewModel.tareas
    val loading by viewModel.loading
    val error by viewModel.error
    var showAddDialog by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var showCompleteDialog by remember { mutableStateOf(false) }
    var tareaToDelete: TareaDTO? by remember { mutableStateOf(null) }
    var tareaToComplete: TareaDTO? by remember { mutableStateOf(null) }


    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(tareas) {
        viewModel.obtenerTareas(token)
        coroutineScope.launch {
            error?.let { snackbarHostState.showSnackbar(it) }
        }
    }

    Column(
        Modifier
            .fillMaxSize()
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
                        onCompleteClick = {
                            tareaToComplete = tarea
                            showCompleteDialog = true  },
                        onDeleteClick = {
                            tareaToDelete = tarea
                            showDeleteDialog = true }
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
            modifier = Modifier
                .padding(16.dp)
                .align(alignment = Alignment.End)

        ) {
            Icon(Icons.Filled.Add, contentDescription = "Añadir tarea")
        }

        // Diálogo para agregar una nueva tarea
        AddTarea(
            showDialog = showAddDialog,
            onDismiss = {showAddDialog = false},
            isAdmin = (userRol == "ADMIN"),
            onConfirm = { titulo, descripcion, asignadoA ->
                val userAsig = if (userRol == "ADMIN") asignadoA else user ?: ""
                if (userAsig.isNotBlank()){
                  viewModel.agregarTarea(
                      TareaDTO(
                      titulo = titulo,
                      descripcion = descripcion,
                      username = userAsig)
                      , token
                  )
                }
                showAddDialog = false
            }
        )

        // Diálogo para completar una tarea
        CompleteSecure(showAlert = showCompleteDialog,
            onConfirm = {
                tareaToComplete?.id?.let { id1 ->
                    viewModel.completarTarea(
                        id1,
                        token
                    )
                }
                showCompleteDialog = false
            },
            onDismiss = {showCompleteDialog = false}
        )

        // Diálogo para eliminar una tarea
        DeleteSecure(showAlert = showDeleteDialog,
            onConfirm = {
                tareaToDelete?.id?.let { id1 ->
                    viewModel.eliminarTarea(
                        id1,
                        token
                    )
                }
                showDeleteDialog = false
            },
            onDismiss = {showDeleteDialog = false}
        )
    }
}

