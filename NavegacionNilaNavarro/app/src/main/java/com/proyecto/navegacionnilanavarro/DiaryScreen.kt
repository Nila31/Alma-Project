package com.proyecto.navegacionnilanavarro

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun DiaryScreen(navController: NavController) {
    // Define un color personalizado con valor hexadecimal rojo claro
    val lightRedColor = Color(0xFFBC8F8F)  // Color rojo claro
    val otono = painterResource(id = R.drawable.otono)

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Fondo con imagen
        Image(
            painter = otono,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .scale(1f)
            .alpha(0.3f), // Ajusta la escala según sea necesario
            contentScale = ContentScale.FillBounds
        )


        // Columna con desplazamiento vertical
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // Top Bar con el título "Mi Diario" y color de fondo rojo claro
            TopAppBar(
                title = {
                    Text(
                        text = "Mi Diario",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                backgroundColor = lightRedColor,
                modifier = Modifier.fillMaxWidth(),
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigateUp()
                        }
                    ) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Atrás")
                    }
                })

            // Spacer para mover el contenido hacia abajo
            Spacer(modifier = Modifier.height(65.dp))

            // Contenedor del mensaje y las imágenes
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                // Mensaje central y cursivo con texto personalizado
                Text(
                    text = "Escribir cuando necesitas expresar tus emociones es una práctica saludable. Te ayuda a liberarte y reflexionar sobre tus sentimientos.",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp),
                )
            }

            // Spacer para mover las imágenes hacia abajo
            Spacer(modifier = Modifier.height(16.dp))

            // Contenedor de las imágenes y textos
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Imagen "Agregar" y texto "Escribir"
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.agregar),
                        contentDescription = "Agregar",
                        modifier = Modifier
                            .size(100.dp)
                            .clickable {
                                // Navegación al composable "WriteDiaryEntry" al hacer clic en "Agregar"
                                navController.navigate(Routes.WriteDiaryEntry)

                                // Mostrar un Toast al hacer clic
                            }
                    )
                    Text(
                        text = "Escribir",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,  // Cambia el color a negro
                        fontSize = 16.sp
                    )
                }

                // Imagen "Ver" y texto "Ver todas las entradas"
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ver),
                        contentDescription = "Ver",
                        modifier = Modifier
                            .size(100.dp)
                            .clickable {
                                navController.navigate(Routes.DiaryEntryDatesList)
                            }
                    )
                    Text(
                        text = "Ver todas las entradas",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,  // Cambia el color a negro
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}


