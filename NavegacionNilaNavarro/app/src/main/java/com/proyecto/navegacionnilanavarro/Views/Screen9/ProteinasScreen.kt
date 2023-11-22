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
fun ProteinasScreen(navController: NavController) {
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
                        text = "Ingesta de Proteínas Magras",
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
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "Las proteínas son nutrientes esenciales que desempeñan un papel fundamental en el mantenimiento de la salud y la función del cuerpo. A medida que envejecemos, el consumo adecuado de proteínas se vuelve aún más crucial para mantener la masa muscular y la fuerza, factores esenciales para una vida activa y autónoma en la tercera edad. Consumir proteínas magras es especialmente importante para las personas mayores, ya que puede contribuir a mejorar la movilidad, la calidad de vida y el bienestar general.",
                style = MaterialTheme.typography.body1.copy(fontSize = 16.sp),

                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = "Algunas fuentes saludables de proteínas magras para adultos mayores incluyen:\n\n" +
                        "- Pollo y pavo sin piel\n" +
                        "- Pescado, como salmón, trucha y sardinas\n" +
                        "- Huevos\n" +
                        "- Legumbres, como lentejas, garbanzos y frijoles\n" +
                        "- Productos lácteos bajos en grasa, como yogur griego\n\n" +
                        "La elección de proteínas magras es esencial para evitar el exceso de grasas saturadas y colesterol, que pueden tener efectos negativos en la salud cardiovascular. Además, el consumo de proteínas puede contribuir a mantener la masa muscular y la densidad ósea, lo que es crucial para prevenir caídas y fracturas en la tercera edad. Es importante controlar la cantidad de proteínas que consumes y ajustarla según tus necesidades individuales. Consulta con un profesional de la salud para determinar la cantidad adecuada para ti.",
                style = MaterialTheme.typography.body2.copy(fontSize = 16.sp),
                textAlign = TextAlign.Justify
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Beneficios de una Ingesta Suficiente de Proteínas:",
                style = MaterialTheme.typography.h6.copy(fontSize = 25.sp),
                textAlign = TextAlign.Justify,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "- Mantenimiento y reparación de tejidos corporales.\n" +
                        "- Fortalecimiento de músculos y huesos.\n" +
                        "- Apoyo a la salud inmunológica.\n" +
                        "- Ayuda en la cicatrización de heridas.\n" +
                        "- Contribución a la sensación de saciedad.",
                style = MaterialTheme.typography.body2.copy(fontSize = 18.sp),
                textAlign = TextAlign.Justify
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Desventajas del Exceso de Proteínas:",
                style = MaterialTheme.typography.h6.copy(fontSize = 25.sp),
                textAlign = TextAlign.Justify,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Si bien las proteínas son esenciales para la salud, es importante no excederse en su consumo. Un exceso de proteínas puede tener efectos negativos, como carga renal adicional o aumento de peso si se consumen en exceso de calorías. Por ello, es recomendable obtener proteínas de fuentes magras y equilibrar su consumo con otros nutrientes esenciales. Consulta con un profesional de la salud para establecer un equilibrio adecuado en tu dieta.",
                style = MaterialTheme.typography.body2.copy(fontSize = 18.sp),
                textAlign = TextAlign.Justify
            )
        }
    }
}