package com.proyecto.navegacionnilanavarro.Views.Screen7

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.navigation.NavController
import com.proyecto.navegacionnilanavarro.ui.theme.Indigo200

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PoliticaSeguridadScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Política de Seguridad", color = Color.White) },
                backgroundColor = Indigo200,
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    "Política de Seguridad y Confidencialidad de ALMA (Apoyo Emocional y Liderazgo para la Mejora del Bienestar en Adultos Mayores)",
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(30.dp))



                Text(
                    "La aplicación móvil ALMA está diseñada para brindarte información valiosa y recomendaciones basadas en evidencia científica para diversas necesidades, como recordatorios de medicamentos, consejos alimenticios, mejora del sueño y actividades interactivas. En ALMA, garantizamos la confidencialidad de tu información y te ofrecemos contenido respaldado por investigaciones confiables.",
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Justify
                )

                Spacer(modifier = Modifier.height(35.dp))


                Text(
                    "Confidencialidad de la Información",
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    "Protección de Tus Datos: En ALMA, nos tomamos muy en serio la seguridad y la privacidad de tus datos personales. Cualquier información que proporciones, como tu nombre, dirección de correo electrónico y preferencias, se mantendrá confidencial y no se compartirá con terceros sin tu consentimiento.",
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Justify
                )

                Spacer(modifier = Modifier.height(35.dp))


                Text(
                    "Seguridad en la Aplicación:",
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    "Hemos implementado medidas de seguridad avanzadas para proteger tus datos de accesos no autorizados, pérdidas o alteraciones. Aunque tomamos precauciones para mantener tus datos seguros, debes recordar que ninguna transmisión de información en línea es completamente invulnerable.",
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Justify
                )

                Spacer(modifier = Modifier.height(35.dp))


                Text(
                    "Recomendaciones Basadas en la Ciencia",
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    "Información Confiable: En ALMA, contamos con asesoría de personas conocedoras del campo para ofrecerte recomendaciones basadas en la última evidencia científica disponible. Queremos brindarte información precisa y actualizada para que puedas tomar decisiones informadas sobre tu bienestar.",
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Justify
                )

                Spacer(modifier = Modifier.height(35.dp))


                Text(
                    "Uso Responsable de la Información",
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    "Para Tu Información: ALMA te ofrece información con fines informativos. Sin embargo, no sustituye el consejo médico, legal o profesional. Antes de tomar decisiones importantes basadas en la información de la aplicación, te recomendamos consultar con profesionales pertinentes.",
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Justify
                )

                Spacer(modifier = Modifier.height(35.dp))

                Text(
                    "Firebase en ALMA:",
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    "Para brindarte una experiencia segura y confiable, utilizamos Firebase, una plataforma de desarrollo de Google. Firebase nos ayuda a administrar y proteger tus datos de manera efectiva, lo que significa que tu información está en buenas manos.",
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Justify
                )

                Text(
                    "Recuerda que ALMA es una herramienta diseñada para mejorar el bienestar y proporcionar apoyo emocional y liderazgo en adultos mayores. Nuestro compromiso es respetar tu privacidad y ofrecerte información confiable para tu beneficio.",
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Justify
                )
            }
        }
    }
}