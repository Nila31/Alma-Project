package com.proyecto.navegacionnilanavarro.Views.Screen6

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.proyecto.navegacionnilanavarro.Models.Pendiente
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.proyecto.navegacionnilanavarro.R
import com.proyecto.navegacionnilanavarro.ViewModel.PendientesViewModel


@Composable
fun PrioridadCard(pendientes: List<Pendiente>, prioridad: String, viewModel: PendientesViewModel) {
    Card(
        elevation = 4.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = " $prioridad",
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(modifier = Modifier.width(8.dp))
            }

            Spacer(modifier = Modifier.height(8.dp))

            if (pendientes.isEmpty()) {
                Text(
                    text = "No hay pendientes en esta categoría",
                    style = MaterialTheme.typography.bodyMedium
                )
            } else {
                for (pendiente in pendientes) {
                    PendienteCard(pendiente, viewModel)
                }
            }
        }
    }
}

@Composable
fun PendienteCard(pendiente: Pendiente, viewModel: PendientesViewModel) {
    Card(
        elevation = 2.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(16.dp)
                .clickable {
                    if (pendiente.id.isNotEmpty()) {
                        Log.d("PendientesViewModel", "Eliminar pendiente con ID: ${pendiente.id}")
                        viewModel.eliminarPendiente(pendiente)
                    } else {
                        Log.d("PendientesViewModel", "El ID del pendiente es una cadena vacía.")
                    }
                }
        ) {
            Column {
                Text(
                    text = pendiente.titulo,
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = pendiente.descripcion,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Icon(
                painter = painterResource(id = R.drawable.borrar),
                contentDescription = null,
                modifier = Modifier.size(40.dp), // Ajusta el tamaño según tus necesidades
                tint = Color.Unspecified
            )
        }
    }
}