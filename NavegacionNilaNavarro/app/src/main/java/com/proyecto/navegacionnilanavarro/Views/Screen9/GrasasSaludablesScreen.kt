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
fun GrasasSaludablesScreen(navController: NavController) {
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
                        text = "Grasas Saludables",
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
                text = "Las grasas saludables son esenciales para mantener una buena salud en la tercera edad. A medida que envejecemos, nuestros cuerpos requieren un cuidado especial y una nutrición adecuada para mantener la vitalidad y prevenir enfermedades. Las grasas saludables desempeñan un papel crucial en este proceso.",
                style = MaterialTheme.typography.body1.copy(fontSize = 16.sp),
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = "Aunque el término 'grasa' a menudo tiene una connotación negativa, es importante entender que existen grasas que son beneficiosas para el organismo. Estas grasas, conocidas como grasas saludables o grasas insaturadas, son fuentes de energía que también contribuyen al funcionamiento adecuado del corazón, el cerebro y otros sistemas importantes del cuerpo.",
                style = MaterialTheme.typography.body1.copy(fontSize = 16.sp),
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(
                text = "Algunas fuentes de grasas saludables que son especialmente beneficiosas para las personas de la tercera edad son:\n\n" +
                        "- Aceite de oliva virgen extra: Rico en antioxidantes y ácidos grasos monoinsaturados, que pueden ayudar a reducir la inflamación y mejorar la salud cardiovascular.\n" +
                        "- Frutos secos: Almendras, nueces y otros frutos secos son excelentes fuentes de grasas saludables, fibra y nutrientes esenciales para la salud ósea.\n" +
                        "- Pescados grasos: Salmón, trucha, sardinas y otros pescados grasos son ricos en ácidos grasos omega-3, que tienen propiedades antiinflamatorias y beneficiosas para la función cerebral.\n" +
                        "- Aguacates: Estos frutos son ricos en grasas monoinsaturadas y también proporcionan fibra y nutrientes esenciales.\n\n" +
                        "- Es importante recordar que, si bien las grasas saludables son beneficiosas, también es esencial moderar su consumo para mantener un equilibrio en la dieta.",
                style = MaterialTheme.typography.body2.copy(fontSize = 16.sp),
                        textAlign = TextAlign.Justify,

                )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Los beneficios de incorporar grasas saludables en la dieta de la tercera edad son numerosos:",
                style = MaterialTheme.typography.h6.copy(fontSize = 25.sp),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp),
                textAlign = TextAlign.Justify,

                )
            Text(
                text = "- Apoyo a la salud del corazón: Las grasas saludables pueden ayudar a reducir el riesgo de enfermedades cardíacas al mantener los niveles de colesterol bajo control y reducir la inflamación.\n" +
                        "-Función cerebral:Los ácidos grasos omega-3 presentes en pescados grasos pueden contribuir a una mejor función cerebral y reducir el riesgo de deterioro cognitivo.\n" +
                        "- Salud de las articulaciones: Las grasas saludables pueden tener propiedades antiinflamatorias que benefician la salud de las articulaciones.\n" +
                        "- Vitalidad y energía: Las grasas son una fuente concentrada de energía, lo que puede ser especialmente importante para mantener la vitalidad en la tercera edad.\n" +
                        "- Absorción de nutrientes: Algunos nutrientes esenciales son solubles en grasa, lo que significa que el consumo adecuado de grasas saludables puede ayudar en su absorción.\n\n" +
                        "- Es importante asesorarse con un profesional de la salud o un dietista antes de realizar cambios significativos en la dieta, especialmente en la tercera edad.",
                style = MaterialTheme.typography.body2.copy(fontSize = 18.sp),
                textAlign = TextAlign.Justify,

                )
        }
    }
}