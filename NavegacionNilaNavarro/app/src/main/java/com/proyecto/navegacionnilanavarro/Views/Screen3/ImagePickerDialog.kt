package com.proyecto.navegacionnilanavarro.Views.Screen3

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.AlertDialog
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.proyecto.navegacionnilanavarro.R
import androidx.compose.ui.Modifier
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ImagePickerDialog(
    onCameraSelected: () -> Unit,
    onGallerySelected: () -> Unit
) {
    var selectedItem by remember { mutableStateOf("Cámara") }

    AlertDialog(
        onDismissRequest = {},
        title = { Text(text = "Selecciona el medio") },
        text = {
            Column {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(50.dp), // Ajusta el espacio horizontal entre los íconos
                    modifier = Modifier.fillMaxWidth()
                ) {
                    IconButton(
                        onClick = {
                            selectedItem = "Cámara"
                            onCameraSelected() // Llama a la acción al seleccionar la cámara
                        },
                        modifier = Modifier.offset(x = 38.dp) // Ajusta el valor para mover el ícono horizontalmente
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.camara),
                            contentDescription = "Cámara",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(64.dp)
                        )
                    }

                    IconButton(
                        onClick = {
                            selectedItem = "Galería"
                            onGallerySelected() // Llama a la acción al seleccionar la galería
                        },
                        modifier = Modifier.offset(x = (58).dp) // Ajusta el valor para mover el ícono horizontalmente
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.galeria),
                            contentDescription = "Galería",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(64.dp)
                        )
                    }
                }
            }
        },
        confirmButton = {
            // No se necesita el botón "Seleccionar" ya que la acción ocurre al seleccionar un ícono
        },
        dismissButton = {
            // No se necesita el botón "Cancelar"
        },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    )
}