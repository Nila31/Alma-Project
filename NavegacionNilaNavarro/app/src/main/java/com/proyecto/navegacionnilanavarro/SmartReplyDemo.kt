package com.proyecto.navegacionnilanavarro

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import  androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.*



@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)

@Composable
fun SmartReplyDemo(navController: NavController) {
    var temaSeleccionado by remember { mutableStateOf("") }
    var mostrarInformacion by remember { mutableStateOf(false) }

    val informacionPorTema = listOf(
        "La aceptación del cambio es fundamental en todas las etapas de la vida, pero se vuelve especialmente relevante en la vejez, donde las transiciones pueden ser frecuentes. Aceptar que la vida está en constante evolución y que adaptarse a nuevas circunstancias es esencial para mantener un equilibrio emocional. Cultivar una mentalidad abierta hacia las experiencias cambiantes permite a los adultos mayores encontrar oportunidades de crecimiento y descubrimiento en cada nueva etapa de la vida.",
        "Las conexiones sociales son pilares fundamentales para el bienestar emocional en la tercera edad. Mantener relaciones significativas proporciona no solo apoyo práctico, sino también una fuente vital de compañía y alegría. Fomentar la participación en actividades sociales, ya sea a través de reuniones familiares, amistades cercanas o grupos comunitarios, contribuye a construir una red de apoyo emocional que puede ser crucial durante momentos desafiantes.",
        "El autocuidado en la vejez va más allá de la atención física; implica cuidar la salud mental con la misma diligencia. Establecer rutinas de sueño saludables, mantenerse físicamente activo y nutrir el cuerpo con una alimentación equilibrada son prácticas esenciales. Además, dedicar tiempo a actividades que generen alegría y relajación contribuye significativamente a mantener una salud mental positiva, fortaleciendo la resiliencia frente a los desafíos diarios.",
        "La vejez a menudo conlleva la experiencia de pérdidas significativas, ya sea por la jubilación, la partida de seres queridos o cambios en la salud. Afrontar el duelo es un proceso individual, pero es esencial encontrar maneras de honrar y recordar a quienes ya no están presentes. Compartir las emociones con amigos o buscar el apoyo de profesionales puede ayudar a elaborar el duelo y encontrar un sentido renovado de propósito en la vida.",
        "La resiliencia emocional es una habilidad que se puede cultivar. Practicar el pensamiento positivo, encontrar significado en las experiencias difíciles y aprender a adaptarse a los desafíos son aspectos clave de la resiliencia. Fomentar la resiliencia implica desarrollar una mentalidad que ve las adversidades como oportunidades para crecer y fortalecer la capacidad de enfrentar los cambios con flexibilidad y determinación.",
    )

    // Create a CoroutineScope
    val coroutineScope = rememberCoroutineScope()

    val painterBackground = painterResource(id = R.drawable.cloud)

    Image(
        painter = painterBackground,
        contentDescription = null,
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .fillMaxHeight()
            .alpha(0.4f),// Ajusta la escala según sea necesario

        contentScale = ContentScale.Crop // Ajusta el contentScale según tus necesidades
    )


    // LazyColumn for vertical scrolling
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = "Hola, soy Alma, tu asistente de apoyo emocional. Estos son algunos temas que podríamos discutir:",
                color = Color.Black,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Spacer(modifier = Modifier.height(28.dp))

        }


        item {
            Text(
                text = "1. Aceptación del Cambio\n" +
                        "2. Conexiones Sociales\n" +
                        "3. Autocuidado y Salud Mental\n" +
                        "4. Enfrentar Pérdidas y Duelos\n" +
                        "5. Fomentar la Resiliencia",
                textAlign = TextAlign.Justify,
                fontSize = 20.sp, // Justified text alignment
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        item {
            Spacer(modifier = Modifier.height(38.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {

                TextField(
                    value = temaSeleccionado,
                    onValueChange = { temaSeleccionado = it },
                    modifier = Modifier
                        .weight(1f)
                        .background(
                            color = Color.LightGray,
                            shape = RoundedCornerShape(1.dp)
                        ) // Ajusta el radio según tu preferencia
                        .padding(8.dp)
                )
                IconButton(
                    onClick = {
                        if (temaSeleccionado.isNotEmpty()) {
                            val numeroTema = temaSeleccionado.toIntOrNull()
                            if (numeroTema != null && numeroTema in 1..informacionPorTema.size) {
                                mostrarInformacion = true
                                // Use CoroutineScope to introduce a delay before resetting the flag
                                coroutineScope.launch {
                                    delay(5000) // Adjust the delay time as needed (in milliseconds)
                                    mostrarInformacion = false
                                }
                            } else {
                                temaSeleccionado = ""
                                mostrarInformacion = false
                            }
                        }
                    }
                ) {
                    Icon(Icons.Default.Send, contentDescription = "Enviar")
                }
            }
        }

        if (mostrarInformacion) {
            val numeroTema = temaSeleccionado.toIntOrNull()
            if (numeroTema != null && numeroTema in 1..informacionPorTema.size) {
                item {
                    Text(
                        text = informacionPorTema[numeroTema - 1],
                        textAlign = TextAlign.Justify,
                        fontSize = 30.sp, // Justified text alignment
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }
            } else {
                item {
                    Text(
                        text = "Por favor, ingresa un número válido del 1 al 6",
                        textAlign = TextAlign.Justify,// Justified text alignment
                        modifier = Modifier.padding(bottom = 16.dp),

                        )
                }
            }
            // Nuevo código para salir y volver a la página anterior

        }
    }
}
