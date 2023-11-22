package com.proyecto.navegacionnilanavarro.Views.Screen6

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.proyecto.navegacionnilanavarro.Models.Pendiente
import com.proyecto.navegacionnilanavarro.ViewModel.PendientesViewModel
import com.proyecto.navegacionnilanavarro.ui.theme.Indigo200

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun VerPendientesScreen(navController: NavController, viewModel: PendientesViewModel) {
    // Lista de prioridades
    val prioridades = listOf("Importante", "Urgente", "Normal", "Baja", "Sin prioridad")

    // Cargar la lista de pendientes cada vez que haya cambios en el ViewModel
    LaunchedEffect(viewModel) {
        viewModel.obtenerListaDePendientes()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Ver Pendientes", color = Color.White)
                },
                backgroundColor = Indigo200,
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("pendientes") }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                }
            )
        }
    ) {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Iterar sobre la lista de prioridades y crear una PrioridadCard para cada prioridad
            for (prioridad in prioridades) {
                val pendientesParaPrioridad = viewModel.pendientesList.filter { it.prioridad == prioridad }
                item {
                    PrioridadCard(pendientesParaPrioridad, prioridad, viewModel)
                }
            }
        }
    }
}