package com.proyecto.navegacionnilanavarro.Views.Screen4

import android.annotation.SuppressLint
import android.util.Log
import android.webkit.WebView
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import com.proyecto.navegacionnilanavarro.ViewModel.MiPantallaViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SleepBetterScreen(navController: NavController) {
    val techniques = generateSleepTechniques()

    val cardColors = listOf(
        Color(0xFFF0D9CF), // Color de fondo de la primera tarjeta
        Color(0xFFE6D4F1), // Color de fondo de la segunda tarjeta
        Color(0xFFC1EBD9), // Color de fondo de la tercera tarjeta
        Color(0xFFF0D9CF), // Color de fondo de la cuarta tarjeta
        Color(0xFFE6D4F1), // Color de fondo de la quinta tarjeta
        Color(0xFFC1EBD9), // Color de fondo de la sexta tarjeta
        Color(0xFFF0D9CF), // Color de fondo de la séptima tarjeta
        Color(0xFFE6D4F1), // Color de fondo de la octava tarjeta
        Color(0xFFC1EBD9), // Color de fondo de la novena tarjeta
        Color(0xFFF0D9CF), // Color de fondo de la décima tarjeta
        Color(0xFFE6D4F1), // Color de fondo de la undécima tarjeta
        Color(0xFFC1EBD9), // Color de fondo de la duodécima tarjeta
        Color(0xFFF0D9CF), // Color de fondo de la decimotercera tarjeta
        Color(0xFFE6D4F1), // Color de fondo de la decimocuarta tarjeta
        Color(0xFFC1EBD9), // Color de fondo de la decimoquinta tarjeta
        Color(0xFFF0D9CF) // Color de fondo de la decimosexta tarjeta
    )

    val GradientGrey = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFE0E0E0), // Gris claro
            Color(0xFFD2D2D2)  // Gris un poco más oscuro
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Técnicas para dormir mejor") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Volver")
                    }
                },
                backgroundColor = Color.Transparent
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = GradientGrey)
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
        ) {
            items(techniques.size) { index ->
                val technique = techniques[index]
                SleepTechniqueItem(
                    technique = technique,
                    onItemClick = {
                        // Agrega aquí la lógica para manejar el clic en una técnica
                    },
                    cardColor = cardColors.getOrElse(index) { Color.White } // Utiliza el color correspondiente o blanco por defecto
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun ShowVideoDialog(videoUrl: String?, onClose: () -> Unit) {
    if (videoUrl != null) {
        val context = LocalContext.current

        val player = remember {
            SimpleExoPlayer.Builder(context).build().apply {
                val mediaItem = MediaItem.fromUri(videoUrl)
                setMediaItem(mediaItem)
                prepare()
                play()
            }
        }

        AndroidView(factory = { ctx ->
            PlayerView(ctx).apply {
                this.player = player
            }
        })

        DisposableEffect(Unit) {
            onDispose {
                player.stop()
                player.release()
            }
        }
    }

    Button(onClick = onClose) {
        Text("Cerrar")
    }
}

@Composable
fun SleepTechniqueItem(
    technique: SleepTechnique,
    onItemClick: () -> Unit,
    cardColor: Color
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onItemClick),
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp),
        backgroundColor = cardColor
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = technique.title,
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = technique.description,
                style = MaterialTheme.typography.body1,
                color = Color.Black
            )
        }
    }
}

fun generateSleepTechniques(): List<SleepTechnique> {
    return listOf(
        SleepTechnique(
            "Respiración profunda",
            "Respira lenta y profundamente. Inhala durante 4 segundos, mantén el aire en tus pulmones durante 4 segundos y exhala durante otros 4 segundos."
        ),
        SleepTechnique(
            "Meditación",
            "Practica la meditación antes de acostarte para calmar tu mente y relajar tu cuerpo."
        ),
        SleepTechnique(
            "Rutina de sueño",
            "Establece una rutina de sueño regular y crea un ambiente tranquilo y oscuro en tu habitación."
        ),
        SleepTechnique(
            "Evita dispositivos electrónicos",
            "Evita el uso de dispositivos electrónicos antes de dormir, ya que la luz azul puede afectar tu calidad de sueño."
        ),
        SleepTechnique(
            "Té de hierbas relajante",
            "Disfruta de una taza de té de hierbas relajante, como manzanilla o lavanda, antes de acostarte."
        ),
        SleepTechnique(
            "Establece un horario de sueño regular",
            "Intenta acostarte y levantarte a la misma hora todos los días para establecer un horario de sueño regular."
        ),
        SleepTechnique(
            "Realiza ejercicio regularmente",
            "Hacer ejercicio regularmente puede ayudar a mejorar la calidad del sueño. Intenta hacer ejercicio al menos 30 minutos al día."
        ),
        SleepTechnique(
            "Crea un ambiente tranquilo",
            "Crea un ambiente tranquilo en tu habitación, utilizando cortinas opacas, tapones para los oídos o una máquina de ruido blanco si es necesario."
        ),
        SleepTechnique(
            "Evita las siestas largas",
            "Evita tomar siestas largas durante el día, ya que pueden interferir con tu capacidad para conciliar el sueño por la noche."
        ),
        SleepTechnique(
            "Limita la ingesta de cafeína",
            "Evita consumir alimentos y bebidas con cafeína, como café, té o chocolate, especialmente antes de dormir."
        ),
        SleepTechnique(
            "Practica la relajación muscular progresiva",
            "La relajación muscular progresiva consiste en tensar y relajar los diferentes grupos musculares para promover la relajación general del cuerpo."
        ),
        SleepTechnique(
            "Utiliza técnicas de visualización",
            "Practica técnicas de visualización positiva antes de dormir, imaginando lugares tranquilos y relajantes."
        ),
        SleepTechnique(
            "Evita comidas pesadas antes de dormir",
            "Evita comer comidas pesadas o abundantes antes de acostarte, ya que pueden dificultar la digestión y afectar tu sueño."
        ),
        SleepTechnique(
            "Mantén tu habitación fresca",
            "Asegúrate de que la temperatura de tu habitación sea fresca y agradable para facilitar el sueño."
        ),
        SleepTechnique(
            "Apaga las luces",
            "Apaga todas las luces innecesarias en tu habitación para crear un ambiente oscuro y propicio para el sueño."
        ),
        SleepTechnique(
            "Evita el consumo de alcohol",
            "Evita consumir alcohol antes de dormir, ya que puede afectar negativamente la calidad de tu sueño."
        )
    )
}

data class SleepTechnique(val title: String, val description: String)


