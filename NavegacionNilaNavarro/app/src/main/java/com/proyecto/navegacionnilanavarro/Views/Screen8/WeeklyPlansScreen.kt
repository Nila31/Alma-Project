package com.proyecto.navegacionnilanavarro.Views.Screen8

import android.annotation.SuppressLint
import android.content.Intent
import android.media.browse.MediaBrowser
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.util.MimeTypes
import com.proyecto.navegacionnilanavarro.R
import com.proyecto.navegacionnilanavarro.Views.Screen9.CardClickable
import com.proyecto.navegacionnilanavarro.Views.Screen9.ColoredCard
import com.proyecto.navegacionnilanavarro.ui.theme.Indigo200
import java.time.format.TextStyle

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ColoredCard(color: Color, imageRes: Int, title: String) {
    Card(
        backgroundColor = color,
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(8.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier.size(110.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = title,
                fontSize = 27.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 25.dp)
            )
        }
    }
}

@Composable
fun CardClickable(
    videoUrl: String,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier.clickable {
            onClick()
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl))
            context.startActivity(intent)
        },
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun WeeklyPlansScreen(navController: NavController) {
    val gradientBackground = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF98FB98), // Verde menta claro
            Color(0xFF6DC6E6)  // Otro color suave (puedes cambiarlo)
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Recomendaciones para la Ansiedad",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = Color.Black,
                        modifier = Modifier.padding(8.dp)
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Atrás"
                        )
                    }
                },
                backgroundColor = Color.Transparent,
                elevation = 0.dp
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(brush = gradientBackground)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                val cardColors = listOf(
                    Color(0xFFF0D9CF),
                    Color(0xFFE6D4F1),
                    Color(0xFFC1EBD9),
                    Color(0xFFF0D9CF),
                    Color(0xFFE6D4F1)
                )

                val images = listOf(
                    R.drawable.caminar,
                    R.drawable.estirado,
                    R.drawable.taichi,
                    R.drawable.yoga,
                    R.drawable.respiracion
                )

                val titles = listOf(
                    "Caminar",
                    "Estiramientos",
                    "Taichi",
                    "Yoga",
                    "Ejercicio de Respiración"
                )

                val videoUrls = listOf(
                    "https://firebasestorage.googleapis.com/v0/b/alma-391216.appspot.com/o/videos%2Fcaminar.mp4?alt=media&token=289c7099-a084-43ee-b26e-57164c9cafda",
                    "https://firebasestorage.googleapis.com/v0/b/alma-391216.appspot.com/o/videos%2Festiramientos.mp4?alt=media&token=3dc68c97-d3fe-41f7-af34-e518a3235c6b",
                    "https://firebasestorage.googleapis.com/v0/b/alma-391216.appspot.com/o/videos%2Ftaichi.mp4?alt=media&token=91a863e8-cea6-4e83-931e-e3fe6f36fb97",
                    "https://firebasestorage.googleapis.com/v0/b/alma-391216.appspot.com/o/videos%2Fyoga.mp4?alt=media&token=06a31766-60c0-47e0-96fd-e2aa2f97b8b7",
                    "https://firebasestorage.googleapis.com/v0/b/alma-391216.appspot.com/o/videos%2Frespiracionprofund.mp4?alt=media&token=d2a40204-3565-4554-b1d1-26185fdbc4da"
                )

                for ((index, color) in cardColors.withIndex()) {
                    CardClickable(
                        videoUrl = videoUrls[index],
                        onClick = { /* Realizar la navegación aquí */ },
                        content = {
                            ColoredCard(color = color, imageRes = images[index], title = titles[index])
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    )
}