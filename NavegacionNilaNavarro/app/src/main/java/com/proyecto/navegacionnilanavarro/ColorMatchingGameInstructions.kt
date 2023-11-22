package com.proyecto.navegacionnilanavarro

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.proyecto.navegacionnilanavarro.ui.theme.PurpleGrey40

@Composable
fun ColorMatchingGameInstructions(navController: NavController) {
    val painterBackground = painterResource(id = R.drawable.onda)
    Image(
        painter = painterBackground,
        contentDescription = null,
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .fillMaxHeight(),
        contentScale = ContentScale.Crop // Ajusta el contentScale según tus necesidades
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()) // Agregar desplazamiento vertical
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "¡Bienvenido al Juego de Coincidencia de Vegetales!",
            color = Color.Black,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Spacer(modifier = Modifier.height(60.dp)) // Espacio entre el primer texto y la imagen circular

        Text(
            text = "Instrucciones:",
            color = Color.Black,
            fontSize = 28.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Spacer(modifier = Modifier.height(50.dp)) // Espacio entre el primer texto y la imagen circular

        Text(
            text = "1. Selecciona los vegetales coincidentes en el tablero.",
            color = Color.Black,
            fontSize = 24.sp,
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "2. Cada acierto aumentará tu puntaje.",
            color = Color.Black,
            fontSize = 24.sp,
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "3. El juego termina cuando se completa el tablero o se agota el tiempo.",
            color = Color.Black,
            fontSize = 24.sp,
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Spacer(modifier = Modifier.height(75.dp)) // Espacio entre el primer texto y la imagen circular

        Button(
            onClick = {
                navController.navigate("ColorMatchingGame")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(PurpleGrey40)
        ) {
            Text("Iniciar el Juego", color = Color.Black, fontSize = 24.sp)
        }
    }
}