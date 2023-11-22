package com.proyecto.navegacionnilanavarro.Views.Screen5

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.proyecto.navegacionnilanavarro.Models.Reminder
import com.proyecto.navegacionnilanavarro.R
import com.proyecto.navegacionnilanavarro.ViewModel.ReminderViewModel
import com.proyecto.navegacionnilanavarro.ui.theme.Indigo200
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ReminderForm(navController: NavController, viewModel: ReminderViewModel, userId: String) {

    val medicationNameState = remember { mutableStateOf("") }
    val dosageState = remember { mutableStateOf("") }
    val timeFrequencyState = remember { mutableStateOf<String?>(null) }
    val timeInMinutesState = remember { mutableStateOf(0) }
    val isAmPmSelectedState = remember { mutableStateOf(true) }
    val showSuccessMessage = remember { mutableStateOf(false) }
    val expandedState =
        remember { mutableStateOf(false) } // Estado para controlar la expansión del menú

    // Función para validar que todos los campos estén completos
    fun areFieldsValid(): Boolean {
        return medicationNameState.value.isNotEmpty() &&
                dosageState.value.isNotEmpty() &&
                (timeFrequencyState.value != null || timeInMinutesState.value > 0)
    }

    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    val pastas = painterResource(id = R.drawable.pastas)

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Fondo con imagen
        Image(
            painter = painterResource(id = R.drawable.pastas),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.8f), // Ajusta la opacidad según sea necesario
            contentScale = ContentScale.FillBounds
        )


        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            "Agregar recordatorio",
                            style = MaterialTheme.typography.h6.copy(color = Color.White)
                        )
                    },
                    backgroundColor = Indigo200,
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
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
                    value = medicationNameState.value,
                    onValueChange = { medicationNameState.value = it },
                    label = { Text("Nombre del medicamento") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = dosageState.value,
                    onValueChange = { dosageState.value = it },
                    label = { Text("Dosis") },
                    modifier = Modifier.fillMaxWidth()
                )

                // DropdownMenu para seleccionar la frecuencia de tiempo
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Seleccionar frecuencia de tiempo",
                        fontSize = 20.sp, // Ajusta el tamaño de fuente según tus preferencias
                        modifier = Modifier
                            .padding(vertical = 18.dp, horizontal = 16.dp)
                        // Abrir el menú al hacer clic
                    )
                    Spacer(modifier = Modifier.height(40.dp))


                    Image(
                        painter = painterResource(id = R.drawable.calendario), // Agrega el ID de la imagen de calendario
                        contentDescription = null,
                        modifier = Modifier
                            .padding(top = 50.dp)
                            .clickable(onClick = {
                                expandedState.value = true
                            }) // También abre el menú al hacer clic en la imagen
                    )

                    DropdownMenu(
                        expanded = expandedState.value, // Controlar la expansión del menú
                        onDismissRequest = {
                            expandedState.value = false
                        }, // Cerrar el menú al hacer clic fuera de él
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        DropdownMenuItem(onClick = {
                            timeFrequencyState.value = "Ahora"
                            expandedState.value = false // Cerrar el menú al seleccionar una opción
                        }) {
                            Text("Ahora")
                        }
                        DropdownMenuItem(onClick = {
                            timeFrequencyState.value = "En 1 minuto"
                            expandedState.value = false // Cerrar el menú al seleccionar una opción
                        }) {
                            Text("En 1 minuto")
                        }
                        DropdownMenuItem(onClick = {
                            timeFrequencyState.value = "En 5 minutos"
                            expandedState.value = false // Cerrar el menú al seleccionar una opción
                        }) {
                            Text("En 5 minutos")
                        }
                        DropdownMenuItem(onClick = {
                            timeFrequencyState.value = "En 30 minutos"
                            expandedState.value = false // Cerrar el menú al seleccionar una opción
                        }) {
                            Text("En 30 minutos")
                        }
                        DropdownMenuItem(onClick = {
                            timeFrequencyState.value = "En 1 hora"
                            expandedState.value = false // Cerrar el menú al seleccionar una opción
                        }) {
                            Text("En 1 hora")
                        }
                        DropdownMenuItem(onClick = {
                            timeFrequencyState.value = "En 2 horas"
                            expandedState.value = false // Cerrar el menú al seleccionar una opción
                        }) {
                            Text("En 2 horas")
                        }
                        DropdownMenuItem(onClick = {
                            timeFrequencyState.value = "En 4 horas"
                            expandedState.value = false // Cerrar el menú al seleccionar una opción
                        }) {
                            Text("En 4 horas")
                        }
                        DropdownMenuItem(onClick = {
                            timeFrequencyState.value = "En 6 horas"
                            expandedState.value = false // Cerrar el menú al seleccionar una opción
                        }) {
                            Text("En 6 horas")
                        }
                        DropdownMenuItem(onClick = {
                            timeFrequencyState.value = "En 8 horas"
                            expandedState.value = false // Cerrar el menú al seleccionar una opción
                        }) {
                            Text("En 8 horas")
                        }
                        DropdownMenuItem(onClick = {
                            timeFrequencyState.value = "En 12 horas"
                            expandedState.value = false // Cerrar el menú al seleccionar una opción
                        }) {
                            Text("En 12 horas")
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
                            val reminder = Reminder(
                                medicationName = medicationNameState.value,
                                dosage = dosageState.value,
                                timeFrequency = timeFrequencyState.value,
                                timeInMinutes = timeInMinutesState.value,
                                isAmPmSelected = isAmPmSelectedState.value,
                                userId = userId
                            )

                            if (areFieldsValid()) {
                                viewModel.saveReminder(reminder)
                                showSuccessMessage.value = true

                                coroutineScope.launch {
                                    delay(5000)
                                    showSuccessMessage.value = false
                                }
                            } else {
                                Toast.makeText(
                                    context,
                                    "Por favor, completa todos los campos",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        },
                        shape = RoundedCornerShape(percent = 50),
                        modifier = Modifier.fillMaxSize(),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.White,
                            backgroundColor = Indigo200
                        )
                    ) {
                        Text("Guardar")
                    }

                    if (showSuccessMessage.value) {
                        // Mostrar mensaje de éxito con Toast
                        Toast.makeText(
                            LocalContext.current,
                            "Recordatorio guardado con éxito",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    LaunchedEffect(Unit) {
                        navController.addOnDestinationChangedListener { _, destination, _ ->
                            if (destination.route == "menu") {
                                medicationNameState.value = ""
                                dosageState.value = ""
                                timeFrequencyState.value = null
                                timeInMinutesState.value = 0
                                isAmPmSelectedState.value = true
                                showSuccessMessage.value = false
                            }
                        }
                    }
                }
            }
        }
    }
}