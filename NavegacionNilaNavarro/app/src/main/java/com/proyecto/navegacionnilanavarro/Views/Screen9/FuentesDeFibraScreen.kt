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
fun FuentesDeFibraScreen(navController: NavController) {
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
                        text = "Fuentes de Fibra",
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
                text = "La fibra es un nutriente esencial para mantener la salud en la tercera edad. Consumir suficiente fibra en la dieta puede contribuir a mantener una digestión saludable y prevenir problemas comunes como el estreñimiento, que pueden afectar la calidad de vida.",
                style = MaterialTheme.typography.body1.copy(fontSize = 16.sp),
                textAlign = TextAlign.Justify, // Añadir esta línea
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = "La fibra es especialmente beneficiosa para las personas mayores, ya que puede ayudar a resolver problemas digestivos que pueden ser más frecuentes en esta etapa de la vida. También puede ofrecer una serie de otros beneficios que son esenciales para mantener la salud y el bienestar.",
                style = MaterialTheme.typography.body1.copy(fontSize = 16.sp),
                textAlign = TextAlign.Justify, // Añadir esta línea
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = "Algunas fuentes de fibra recomendadas para adultos mayores son:\n\n" +
                        "- Cereales integrales, como avena y arroz integral.\n" +
                        "- Frutas frescas y secas, como manzanas, peras y ciruelas pasas.\n" +
                        "- Verduras de hojas verdes, como espinacas y acelgas.\n" +
                        "- Legumbres, como lentejas, garbanzos y frijoles.\n\n" +
                        "Aumentar gradualmente la ingesta de fibra y mantenerse hidratado puede ayudar a prevenir molestias digestivas y promover una mejor calidad de vida.",
                style = MaterialTheme.typography.body2.copy(fontSize = 16.sp),
                        textAlign = TextAlign.Justify, // Añadir esta línea

            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Beneficios de Consumir Fuentes de Fibra:",
                style = MaterialTheme.typography.h6.copy(fontSize = 25.sp),
                textAlign = TextAlign.Justify, // Añadir esta línea
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "- Promueve una digestión saludable y previene el estreñimiento, aliviando molestias digestivas comunes.\n" +
                        "- Ayuda a controlar los niveles de azúcar en sangre, lo que es especialmente beneficioso para personas con diabetes.\n" +
                        "- Contribuye a la sensación de saciedad y puede ayudar en el control de peso.\n" +
                        "- Puede reducir el riesgo de enfermedades del corazón al ayudar a controlar los niveles de colesterol.\n" +
                        "- Mejora la salud intestinal y el bienestar general, promoviendo un sistema digestivo más eficiente.",
                style = MaterialTheme.typography.body2.copy(fontSize = 18.sp),
                textAlign = TextAlign.Justify,

                )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Consecuencias de No Consumir Suficiente Fibra:",
                style = MaterialTheme.typography.h6.copy(fontSize = 25.sp),
                textAlign = TextAlign.Justify, // Añadir esta línea
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "No consumir suficiente fibra en la dieta puede tener un impacto negativo en la salud de las personas mayores:\n\n" +
                        "- Mayor riesgo de estreñimiento y problemas digestivos, que pueden afectar la calidad de vida.\n" +
                        "- Dificultad para mantener niveles saludables de colesterol y azúcar en sangre.\n" +
                        "- Sensación de hambre más frecuente debido a la falta de saciedad.\n" +
                        "- Mayor riesgo de enfermedades del corazón y diabetes tipo 2.\n" +
                        "- Menor salud intestinal y mayor susceptibilidad a problemas gastrointestinales.\n\n" +
                        "Asegurarse de incluir fuentes de fibra en la dieta diaria es esencial para mantener una buena salud en la tercera edad.",
                style = MaterialTheme.typography.body2.copy(fontSize = 18.sp),
                textAlign = TextAlign.Justify,
            )
        }
    }
}