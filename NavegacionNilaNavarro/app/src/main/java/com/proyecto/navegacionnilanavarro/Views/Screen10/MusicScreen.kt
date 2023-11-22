package com.proyecto.navegacionnilanavarro.Views.Screen10

import android.annotation.SuppressLint
import android.media.MediaPlayer
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.proyecto.navegacionnilanavarro.R

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MusicScreen(navController: NavController) {
    val mediaPlayer = remember { MediaPlayer() }
    val gradientBackground = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFB2DFFC), // Azul claro
            Color(0xFF6DC6E6)  // Azul medio
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Música Terapia",
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                backgroundColor = Color(0xFF9FA8DA), // Azul pálido
                modifier = Modifier.fillMaxWidth()
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = gradientBackground)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            val pulseAnim = rememberInfiniteTransition()
            val pulseValue by pulseAnim.animateFloat(
                initialValue = 1f,
                targetValue = 1.2f,
                animationSpec = infiniteRepeatable(
                    animation = keyframes {
                        durationMillis = 1000
                        0.5f at 500
                    },
                    repeatMode = RepeatMode.Restart
                )
            )

            Image(
                painter = painterResource(id = R.drawable.musica), // Reemplaza con tu recurso
                contentDescription = null,
                modifier = Modifier
                    .size(180.dp)
                    .clip(CircleShape)
                    .scale(pulseValue)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "La música tiene el poder de sanar el alma y calmar la mente. La terapia musical ha demostrado efectos positivos en la reducción del estrés, la mejora del estado de ánimo y el fomento de la relajación.",
                fontSize = 16.sp,
                color = Color.Black, // Cambia al color deseado
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Italic, // Texto en cursiva
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                addIcon("arbol", "https://firebasestorage.googleapis.com/v0/b/alma-391216.appspot.com/o/videos%2Fnaturaleza-para-relajarse-1-.mp3?alt=media&token=b0ff8b4c-4eea-43ad-ade1-6147a51c078c", mediaPlayer)
                addIcon("atmosfera", "https://firebasestorage.googleapis.com/v0/b/alma-391216.appspot.com/o/videos%2Fla-atmosfera_4.mp3?alt=media&token=47230876-8a79-41fb-ae1d-d89c3b1d79b6", mediaPlayer)
                addIcon("playa", "https://firebasestorage.googleapis.com/v0/b/alma-391216.appspot.com/o/videos%2Fplaya_1.mp3?alt=media&token=0686c6f3-de9f-4352-89dc-723e28e2bdc9", mediaPlayer)
                addIcon("gotas", "https://firebasestorage.googleapis.com/v0/b/alma-391216.appspot.com/o/videos%2Fpor-goteo_2.mp3?alt=media&token=10f55e48-c908-4143-b057-43b8e222c496", mediaPlayer)
                addIcon("mar", "https://firebasestorage.googleapis.com/v0/b/alma-391216.appspot.com/o/videos%2Fsea24.mp3?alt=media&token=d59a38af-7cb0-4f54-b3aa-56da6eb3d8fa", mediaPlayer)
                addIcon("pajaro", "https://firebasestorage.googleapis.com/v0/b/alma-391216.appspot.com/o/videos%2Faves_24.mp3?alt=media&token=16cd8416-b157-4daf-b430-20818f17de0e", mediaPlayer)
            }
        }
    }
}

@Composable
fun addIcon(iconName: String, soundUrl: String, mediaPlayer: MediaPlayer) {
    val iconResource = getImageResourceByName(iconName)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(
            horizontal = 16.dp,
            vertical = 35.dp
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Icono clickeable para reproducir sonido
            Icon(
                painter = painterResource(id = iconResource),
                contentDescription = iconName,
                tint = Color.Unspecified,
                modifier = Modifier.size(80.dp)
                    .clickable {
                        if (mediaPlayer.isPlaying) {
                            mediaPlayer.pause()
                        } else {
                            mediaPlayer.reset() // Resetea el MediaPlayer antes de cargar un nuevo sonido
                            mediaPlayer.setDataSource(soundUrl)
                            mediaPlayer.prepare()
                            mediaPlayer.start()
                        }
                    }
            )

            // Icono "pausar" clickeable
            Icon(
                painter = painterResource(id = R.drawable.pausar),
                contentDescription = "Pausa",
                tint = Color.Unspecified,
                modifier = Modifier.size(24.dp)
                    .clickable {
                        if (mediaPlayer.isPlaying) {
                            mediaPlayer.pause()
                        } else {
                            mediaPlayer.start()
                        }
                    }
            )
        }
    }
}

@Composable
fun getImageResourceByName(name: String): Int {
    val resources = LocalContext.current.resources
    return resources.getIdentifier(
        name,
        "drawable",
        LocalContext.current.packageName
    )
}