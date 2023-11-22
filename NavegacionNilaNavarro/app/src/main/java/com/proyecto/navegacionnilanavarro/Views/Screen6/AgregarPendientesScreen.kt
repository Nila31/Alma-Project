package com.proyecto.navegacionnilanavarro.Views.Screen6

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.RadioButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.proyecto.navegacionnilanavarro.Models.Pendiente
import com.proyecto.navegacionnilanavarro.ViewModel.PendientesViewModel
import com.proyecto.navegacionnilanavarro.ui.theme.Indigo200
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.runtime.*


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AgregarPendientesScreen(navController: NavController, viewModel: PendientesViewModel, userId: String) {
    var tituloState by remember { mutableStateOf("") }
    var descripcionState by remember { mutableStateOf("") }
    var prioridadState by remember { mutableStateOf("") }
    var showSuccessMessage by remember { mutableStateOf(false) }
    var showError by remember { mutableStateOf(false) }

    // Lista de opciones de prioridad
    val prioridades = listOf("Importante", "Urgente", "Normal", "Baja", "Sin prioridad")

    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Agregar pendientes", style = MaterialTheme.typography.h6.copy(color = Color.White))
                },
                backgroundColor = Indigo200,
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("WelcomeScreen") }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                actions = {
                    // Agregar el icono de los tres puntos con menú desplegable
                    var expanded by remember { mutableStateOf(false) }
                    IconButton(onClick = { expanded = true }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Options", tint = Color.White)
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(onClick = {
                            // Acción al hacer clic en "Ver pendientes"
                            navController.navigate("verpendientes")
                            expanded = false
                        }) {
                            Text("Ver pendientes")
                        }

                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = tituloState,
                onValueChange = { tituloState = it },
                label = { Text("Título") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = descripcionState,
                onValueChange = { descripcionState = it },
                label = { Text("Descripción") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))


            // RadioButtons para seleccionar la prioridad del pendiente
            Column(
                verticalArrangement = Arrangement.spacedBy(18.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Prioridad")
                prioridades.forEach { prioridad ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        RadioButton(
                            selected = prioridad == prioridadState,
                            onClick = { prioridadState = prioridad }
                        )
                        Text(prioridad)
                    }
                }
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(8.dp)
            ) {
                Button(
                    onClick = {
                        if (tituloState.isNotEmpty() && descripcionState.isNotEmpty() && prioridadState.isNotEmpty()) {
                            val pendiente = Pendiente(
                                titulo = tituloState,
                                descripcion = descripcionState,
                                prioridad = prioridadState,
                                userId = userId
                            )

                            viewModel.guardarPendiente(userId, pendiente)

                            // Mostrar mensaje de éxito por 3 segundos
                            showSuccessMessage = true
                            coroutineScope.launch {
                                delay(3000)
                                showSuccessMessage = false
                            }

                            // Reiniciar campos del formulario
                            tituloState = ""
                            descripcionState = ""
                            prioridadState = ""
                        } else {
                            showError = true
                            coroutineScope.launch {
                                delay(3000)
                                showError = false
                            }
                        }
                    },
                    shape = RoundedCornerShape(percent = 50),
                    modifier = Modifier.fillMaxSize(),
                    colors = ButtonDefaults.buttonColors(contentColor = Color.White, backgroundColor = Indigo200)
                ) {
                    Text("Guardar")
                }
            }

            if (showSuccessMessage) {
                Text("Pendiente guardado con éxito")
            }

            if (showError) {
                Text("Lo siento, debe completar la información para guardar el pendiente")
            }

            LaunchedEffect(Unit) {
                navController.addOnDestinationChangedListener { _, destination, _ ->
                    if (destination.route == "menu") {
                        // Reiniciar campos del formulario
                        tituloState = ""
                        descripcionState = ""
                        prioridadState = ""
                        showSuccessMessage = false
                        showError = false
                    }
                }
            }
        }
    }
}