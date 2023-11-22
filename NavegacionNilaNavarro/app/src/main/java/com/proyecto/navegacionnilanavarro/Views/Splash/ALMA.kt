package com.proyecto.navegacionnilanavarro.Views.Splash
import android.annotation.SuppressLint
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.proyecto.navegacionnilanavarro.AudioPlayer
import com.proyecto.navegacionnilanavarro.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun HeartbeatAnimation(navController: NavController) {
    val infiniteTransition = rememberInfiniteTransition()

    // Define la animación de la aureola de luz
    val haloAlpha by infiniteTransition.animateFloat(
        initialValue = 0.1f,
        targetValue = 0.7f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ondulado),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(1.dp, top = 130.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "¡Hola y bienvenid@ a Alma!",
                style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 35.dp)
            )

            Text(
                text = "Donde la tecnología se encuentra con el corazón, y cada toque es una conexión especial.",
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp), // Ajusta el tamaño según sea necesario
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))

            // Agrega la animación de la aureola de luz sobre la imagen "animacion"
            Image(
                painter = painterResource(id = R.drawable.animacion),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(top = 1.dp)
                    .graphicsLayer(alpha = haloAlpha) // Aplica el nivel de transparencia a la aureola
            )

            Spacer(modifier = Modifier.height(30.dp))

            // Indicador de carga estilo "reedita"
            CircularProgressIndicator()

            val scope = rememberCoroutineScope()

            // Simular carga gradual de 5 segundos antes de mostrar la pantalla "Home"
            LaunchedEffect(true) {
                delay(5000) // Ajusta el tiempo de carga gradual según tus necesidades

                // Navegar a la pantalla "Home"
                scope.launch {
                    navController.navigate("home")
                }
            }
        }
    }
}