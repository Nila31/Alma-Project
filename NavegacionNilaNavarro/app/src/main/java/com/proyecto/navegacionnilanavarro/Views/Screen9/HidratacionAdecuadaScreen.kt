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
fun HidratacionAdecuadaScreen(navController: NavController) {
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
                        text = "Hidratación Adecuada",
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
                text = "Mantenerse hidratado es esencial para la salud y el bienestar de las personas de la tercera edad. La hidratación adecuada desempeña un papel crucial en el mantenimiento de diversas funciones corporales y en la prevención de problemas de salud.",
                style = MaterialTheme.typography.body1.copy(fontSize = 16.sp),
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = "A medida que envejecemos, nuestro cuerpo puede experimentar cambios en la regulación de la sed y la capacidad de retener agua. Esto hace que sea aún más importante prestar atención a la ingesta de líquidos y mantener una hidratación adecuada.",
                style = MaterialTheme.typography.body1.copy(fontSize = 16.sp),
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = "Algunos consejos esenciales para mantener una hidratación adecuada en la tercera edad son:\n\n" +
                        "-Beber agua regularmente: Asegúrate de beber agua a lo largo del día, incluso si no sientes sed.\n" +
                        "- Incorporar alimentos hidratantes:** Frutas con alto contenido de agua, como sandías y melones, así como sopas y caldos, pueden contribuir a tu hidratación.\n" +
                        "- Limitar cafeína y azúcares:** Evita el exceso de café y bebidas azucaradas, ya que pueden contribuir a la deshidratación.\n" +
                        "- Prestar atención a las señales de sed:** No ignores la sed, ya que es un indicador de que tu cuerpo necesita líquidos.\n\n" +
                        "Recuerda que las necesidades de hidratación pueden variar según el nivel de actividad física y las condiciones climáticas.",
                style = MaterialTheme.typography.body2.copy(fontSize = 16.sp),
                textAlign = TextAlign.Justify,

                )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Los beneficios de mantener una hidratación adecuada en la tercera edad incluyen:",
                style = MaterialTheme.typography.h6.copy(fontSize = 18.sp),
                textAlign = TextAlign.Justify,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "- Función cerebral: La hidratación adecuada es esencial para mantener una función cerebral óptima y prevenir la fatiga cognitiva.\n" +
                        "- Salud renal: El consumo adecuado de líquidos contribuye a la función renal adecuada y la eliminación de toxinas.\n" +
                        "- Piel y cabello saludables: Una hidratación adecuada puede mejorar la apariencia de la piel y el cabello.\n" +
                        "- Regulación de la temperatura: La hidratación ayuda a regular la temperatura corporal, especialmente en climas cálidos.\n" +
                        "- Bienestar general: Mantenerse hidratado puede aumentar la energía y mejorar el estado de ánimo.\n\n" +
                        "Recuerda que la hidratación es una parte esencial de un estilo de vida saludable en la tercera edad.",
                style = MaterialTheme.typography.body2.copy(fontSize = 18.sp),
                textAlign = TextAlign.Justify,

                )


        }
    }
}