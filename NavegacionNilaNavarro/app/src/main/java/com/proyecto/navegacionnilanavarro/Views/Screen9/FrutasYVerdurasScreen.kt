package com.proyecto.navegacionnilanavarro.Views.Screen9

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FrutasYVerdurasScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color(0xFF9FA8DA), // Color Indigo 200
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Go back",
                            tint = Color.White
                        )
                    }
                },
                title = {
                    Text(
                        text = "Frutas y Verduras Variadas",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(8.dp),
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()) // Habilitar el scroll vertical
        ) {
            Text(
                text = "¡Hola! En esta sección, vamos a hablar sobre la importancia de incluir una variedad de frutas y verduras en tu dieta diaria. A medida que envejecemos, mantener una alimentación saludable se vuelve aún más esencial para cuidar de nuestra salud y bienestar.",
                style = MaterialTheme.typography.body1.copy(fontSize = 18.sp), // Tamaño de fuente ajustado
                modifier = Modifier.padding(bottom = 18.dp),
                textAlign = TextAlign.Justify
            )
            Text(
                text = "Las frutas y verduras son auténticos tesoros nutricionales. Están llenas de vitaminas, minerales y antioxidantes que pueden ayudar a mantener una buena salud en esta etapa de la vida. Aquí te compartiremos algunos consejos sencillos para incorporar más frutas y verduras en tu dieta:",
                style = MaterialTheme.typography.body1.copy(fontSize = 18.sp), // Tamaño de fuente ajustado
                modifier = Modifier.padding(bottom = 16.dp),
                textAlign = TextAlign.Justify
            )
            Text(
                text = "- Opta por una paleta de colores variados. ¡Cuantos más colores, más nutrientes!\n" +
                        "- Elige frutas y verduras frescas y de temporada siempre que puedas.\n" +
                        "- Si no encuentras frescas, las opciones congeladas o enlatadas también son válidas.\n" +
                        "- Anima tus desayunos con frutas en el cereal, el yogur o los batidos.\n" +
                        "- No olvides lavar bien tus productos y, si tienes alguna restricción en tu dieta, ¡consulta con un profesional de la salud!",
                style = MaterialTheme.typography.body2.copy(fontSize = 18.sp), // Tamaño de fuente ajustado
                textAlign = TextAlign.Justify
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Beneficios de Consumir Frutas y Verduras:",
                style = MaterialTheme.typography.h6.copy(fontSize = 25.sp), // Tamaño de fuente ajustado
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Consumir frutas y verduras puede tener un impacto positivo en tu salud. Algunos de los beneficios incluyen:\n\n" +
                        "- Aportan una gran variedad de vitaminas y minerales esenciales para el funcionamiento del cuerpo.\n" +
                        "- La fibra presente en estos alimentos favorece la digestión y mantiene tu sistema digestivo en buen estado.\n" +
                        "- Los antioxidantes pueden ayudar a proteger tus células del daño causado por los radicales libres.\n" +
                        "- Contribuyen a mantener una hidratación adecuada debido a su contenido de agua.\n" +
                        "- Pueden ayudar a reducir el riesgo de enfermedades crónicas, como enfermedades cardíacas y ciertos tipos de cáncer.",
                style = MaterialTheme.typography.body2.copy(fontSize = 18.sp), // Tamaño de fuente ajustado
                textAlign = TextAlign.Justify
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Consecuencias de No Consumir Suficientes Frutas y Verduras:",
                style = MaterialTheme.typography.h6.copy(fontSize = 25.sp), // Tamaño de fuente ajustado
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Es importante tener en cuenta que no consumir suficientes frutas y verduras en tu dieta podría tener ciertas repercusiones en tu salud:\n\n" +
                        "- Puede haber un mayor riesgo de deficiencias de vitaminas y minerales esenciales.\n" +
                        "- La falta de fibra en la dieta podría llevar a problemas de digestión y estreñimiento.\n" +
                        "- Los antioxidantes ausentes en tu dieta podrían dejar tus células más susceptibles al daño.\n" +
                        "- Una hidratación inadecuada podría afectar tu energía y bienestar general.\n" +
                        "- La falta de nutrientes en las frutas y verduras podría aumentar el riesgo de enfermedades crónicas, como enfermedades cardíacas y diabetes.",
                style = MaterialTheme.typography.body2.copy(fontSize = 18.sp), // Tamaño de fuente ajustado
                textAlign = TextAlign.Justify
            )
        }
    }
}