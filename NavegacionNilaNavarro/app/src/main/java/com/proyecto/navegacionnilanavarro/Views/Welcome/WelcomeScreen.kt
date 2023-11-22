package com.proyecto.navegacionnilanavarro.Views.Welcome

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.proyecto.navegacionnilanavarro.R
import com.proyecto.navegacionnilanavarro.ViewModel.UserViewModel
import com.proyecto.navegacionnilanavarro.Views.Screen1.NavigationBar
import com.proyecto.navegacionnilanavarro.ui.theme.Indigo200
import kotlinx.coroutines.delay

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable fun WelcomeScreen  (navController: NavController, context: Context = LocalContext.current, viewModel: UserViewModel) {
    val auth = FirebaseAuth.getInstance()
    val isUserLoggedIn = remember { mutableStateOf(true) } // Estado de inicio de sesión

    val responses = mapOf(
        "feliz" to "¡Nos alegra que estés feliz hoy! Estamos aquí para ayudarte a mantener ese buen ánimo y hacer que tu día sea aún mejor.",
        "triste" to "Sentimos que te sientas triste. Nuestra aplicación ofrece música que puede ayudarte a despejarte",
        "enojado" to "Es completamente normal experimentar enojo en ciertas situaciones, sin embargo, puedes beneficiarte al practicar ejercicios de respiración profunda, respaldados por la ciencia, para gestionar esta emoción de manera más efectiva",
        "nervioso" to "Sentirse nervioso es normal. Explora nuestras opciones para encontrar la calma",
        "cansado" to "Si te sientes cansado, tómate un momento para relajarte. En nuestra aplicación, encontrarás consejos que puedes poner en práctica para tener una mejor calidad de sueño.",
        "ansioso" to "La ansiedad es algo a lo que todos nos enfrentamos a veces. Encuentra herramientas en nuestra app para gestionarla",
        "aburrido" to "Si te sientes aburrido, nuestra app te ofrece actividades y entretenimiento",
        "abrumado" to "Sentirse abrumado puede ser duro. Encuentra apoyo en nuestra comunidad"
    )

    var selectedEmotion by remember { mutableStateOf("") }

    // Esta variable controlará si se muestra el mensaje
    var showMessage by remember { mutableStateOf(false) }

    // Lanzar el efecto cuando showMessage cambia a true
    LaunchedEffect(showMessage) {
        if (showMessage) {
            delay(8000) // Mostrar el mensaje durante 5 segundos
            showMessage = false // Ocultar el mensaje después de 5 segundos
            when (selectedEmotion) {
                "ansioso" -> navController.navigate("WeeklyPlansScreen") // Navegar a "WeeklyPlansScreen" si se selecciona "ansioso"
                "cansado" -> navController.navigate("SleepBetterScreen")
                "triste" -> navController.navigate("MusicScreen")// Navegar a "SleepBetterScreen" si se selecciona "cansado"
                "enojado" -> navController.navigate("BreathingExercise")// // Navegar a "SleepBetterScreen" si se selecciona "cansado"
                "nervioso" -> navController.navigate("NerviososScreen")//
                "abrumado" -> navController.navigate("DiaryScreen")//
                "aburrido" -> navController.navigate("BoringScreen")//
                else -> navController.navigate("happy") // Navegar a "happy" en otros casos
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {

                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("home") }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    // Icono de Ayuda
                    IconButton(
                        onClick = {
                            navController.navigate("SmartReplyDemo")
                        },
                        modifier = Modifier.size(50.dp) // Ajusta el tamaño según sea necesario
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.circular_image), // Reemplaza R.drawable.ayuda con el nombre de tu imagen
                            contentDescription = null, // Puedes quitar esto si no deseas una descripción
                            modifier = Modifier.fillMaxSize()
                                .clip(CircleShape) // Ajusta el tamaño de la imagen para que llene completamente el IconButton
                        )
                    }

                    var expanded by remember { mutableStateOf(false) }

                    IconButton(
                        onClick = { expanded = true },
                        modifier = Modifier.size(40.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier.background(Color.Transparent)
                    ) {
                        DropdownMenuItem(
                            onClick = {
                                navController.navigate("pendientes")
                                expanded = false
                            }
                        ) {
                            Text("Agenda")
                        }

                        DropdownMenuItem(
                            onClick = {
                                navController.navigate("recordatorio")
                                expanded = false
                            }
                        ) {
                            Text("Recordatorios de medicamentos")
                        }
                    }
                },
                backgroundColor = Color(0xFF9FA8DA),
                modifier = Modifier.fillMaxWidth()
            )
        },
        bottomBar = {
            NavigationBar(navController = navController, isUserLoggedIn = isUserLoggedIn)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "¡Bienvenid@!",
                fontWeight = FontWeight.Bold,
                fontSize = 50.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .padding(bottom = 50.dp)
            )

            Text(
                text = "Nos preocupamos por ti y tu estado de ánimo. ¿Cómo te sientes hoy?",
                fontSize = 30.sp,
                color = Color.Black,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 50.dp)
            )

            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                val emotionPairs = listOf(
                    listOf("feliz", "triste"),
                    listOf("enojado", "nervioso"),
                    listOf("cansado", "ansioso"),
                    listOf("aburrido", "abrumado")
                )

                emotionPairs.forEach { pair ->

                    item {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(65.dp), // Espacio entre emociones
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically // Centrar emociones verticalmente
                        ) {
                            pair.forEach { emotion ->
                                val drawableResId = context.resources.getIdentifier(
                                    emotion,
                                    "drawable",
                                    context.packageName
                                )
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier.clickable {
                                        selectedEmotion = emotion
                                        viewModel.updateEstadoAnimo(auth.currentUser?.uid ?: "", emotion)
                                        showMessage = true
                                    }
                                        .padding(start = 16.dp) // Ajusta el contenido hacia la derecha
                                ) {
                                    Text(
                                        text = emotion.capitalize(),
                                        color = Color.Black,
                                        fontSize = 16.sp,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.padding(bottom = 1.dp)
                                    )
                                    Spacer(modifier = Modifier.height(1.dp))
                                    Image(
                                        painter = painterResource(id = drawableResId),
                                        contentDescription = null,
                                        modifier = Modifier.size(100.dp)
                                    )

                                }
                            }
                        }
                    }
                }
            }

            if (showMessage && selectedEmotion.isNotEmpty()) {
                AlertDialog(
                    onDismissRequest = {
                        showMessage = false
                        selectedEmotion = ""
                        when (selectedEmotion) {
                            "ansioso" -> navController.navigate("WeeklyPlansScreen") // Navegar a "WeeklyPlansScreen" si se selecciona "ansioso"
                            "cansado" -> navController.navigate("SleepBetterScreen")
                            "triste" -> navController.navigate("MusicScreen") // Navegar a "SleepBetterScreen" si se selecciona "cansado"
                            "enojado" -> navController.navigate("BreathingExercise") // Navegar a "SleepBetterScreen" si se selecciona "cansado"
                            "nervioso" -> navController.navigate("NerviososScreen")//
                            "abrumado" -> navController.navigate("DiaryScreen")//
                            "aburrido" -> navController.navigate("BoringScreen")//
                            else -> navController.navigate("happy") // Navegar a "Happy" en otros casos
                        }
                    },
                    title = {
                        Text(text = "Recordatorio")
                    },
                    text = {
                        Text(text = responses[selectedEmotion] ?: "")
                    },
                    buttons = {}
                )
            }
        }
    }
}