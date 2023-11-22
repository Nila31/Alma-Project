package com.proyecto.navegacionnilanavarro

import android.annotation.SuppressLint
import android.os.Build.VERSION.SDK_INT
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.material3.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.proyecto.navegacionnilanavarro.ui.theme.Indigo200

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
enum class BreathState {
    INHALE,
    EXHALE
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BreathingExercise(navController: NavController) {
    var breathState by remember { mutableStateOf(BreathState.INHALE) }

    val breathAnimation by animateFloatAsState(
        targetValue = if (breathState == BreathState.INHALE) 1f else 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(4000),
            repeatMode = RepeatMode.Reverse
        )
    )
    val cloudImage = painterResource(id = R.drawable.cloud)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Ejercicio de respiración profunda",
                        style = MaterialTheme.typography.h6,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            // Handle navigation back
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                backgroundColor = Color.Blue
            )
        },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = cloudImage,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                    .scale(3f), // Ajusta la escala según sea necesario

                contentScale = ContentScale.FillBounds
                )

                Canvas(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                        .background(Color.Gray)
                        .padding(8.dp)
                ) {
                    drawRoundRect(
                        color = Color.White,
                        size = size.copy(height = size.height * breathAnimation),
                        cornerRadius = CornerRadius.Zero
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val textToShow = when {
                        breathAnimation >= 0.90f -> "Inhala"
                        breathAnimation <= 0.05f -> "Exhala"
                        else -> ""
                    }

                    Text(
                        text = textToShow,
                        style = MaterialTheme.typography.h5,
                        color = Color.Black,
                        modifier = Modifier
                            .animateContentSize(
                                animationSpec = tween(2000)
                            )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    IconButton(
                        onClick = {
                            breathState =
                                if (breathState == BreathState.INHALE) BreathState.EXHALE else BreathState.INHALE
                        },
                        modifier = Modifier
                            .size(72.dp)
                            .background(Color.Blue, CircleShape)
                    ) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            }
        }
    )
}