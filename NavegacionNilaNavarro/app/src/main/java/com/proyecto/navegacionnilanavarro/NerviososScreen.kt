package com.proyecto.navegacionnilanavarro

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.proyecto.navegacionnilanavarro.Views.Screen9.CardClickable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NerviososScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Agregar la imagen de fondo
        Image(
            painter = painterResource(id = R.drawable.pelotas), // Reemplaza con la imagen de fondo deseada
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // Contenido
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(36.dp)
                        .clickable {
                            navController.popBackStack()
                        }
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Descubre la Serenidad en la Adversidad: Técnicas de Relajación para Aliviar el Nerviosismo",
                    fontSize = 24.sp,
                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Spacer(modifier = Modifier.height(30.dp))

                val cardData = listOf(
                    CardData(
                        imagenResId = R.drawable.diafragmatica,
                        titulo = "Respiración Diafragmática",
                        url = "https://firebasestorage.googleapis.com/v0/b/alma-391216.appspot.com/o/videos%2Fdiagrafmatica.mp4?alt=media&token=36fd90c7-0da4-4b92-97b6-793bca2d17b6&_gl=1*l682lm*_ga*MjAwODEwMzI4NS4xNjk3NjM3OTc3&_ga_CW55HF8NVT*MTY5ODc3Mzk2NC4xMi4xLjE2OTg3NzU4MTIuNDAuMC4w"
                    ),

                    CardData(
                        imagenResId = R.drawable.jacobson,
                        titulo = "Técnica de Relajación de Jacobson",
                        url = "https://firebasestorage.googleapis.com/v0/b/alma-391216.appspot.com/o/videos%2Fjabson.mp4?alt=media&token=db845783-9d41-4f00-b50b-110ff6305a6d&_gl=1*whuj8q*_ga*MjAwODEwMzI4NS4xNjk3NjM3OTc3&_ga_CW55HF8NVT*MTY5ODc3Mzk2NC4xMi4xLjE2OTg3NzU4MjguMjQuMC4w"
                    ),
                    CardData(
                        imagenResId = R.drawable.mindfulness,
                        titulo = "Meditación - Mindfulness",
                        url = "https://firebasestorage.googleapis.com/v0/b/alma-391216.appspot.com/o/videos%2FMeditación%20%20Mindfulness.mp4?alt=media&token=a66d918f-0fb2-4467-a649-442ed1e1b21b&_gl=1*1k44enk*_ga*MjAwODEwMzI4NS4xNjk3NjM3OTc3&_ga_CW55HF8NVT*MTY5ODc3Mzk2NC4xMi4x.LjE2OTg3NzU3OTQuNTguMC.4w"
                    )
                )

                cardData.forEach { card ->
                    CardClickable(
                        videoUrl = card.url,
                        onClick = { /* Realizar la navegación aquí */ },
                        content = {
                            RectanguloConImagen(
                                imagenResId = card.imagenResId,
                                titulo = card.titulo
                            )
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}
data class CardData(
    val imagenResId: Int,
    val titulo: String,
    val url: String
)

@Composable
fun RectanguloConImagen(imagenResId: Int, titulo: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(10.dp)
            .clip(RoundedCornerShape(10.dp))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = titulo,
                color = Color.Black,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth() // Para centrar el título
            )
            Image(
                painter = painterResource(id = imagenResId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(1f)
            )
        }
    }
}
@Composable
fun CardClickable(videoUrl: String, onClick: () -> Unit, content: @Composable () -> Unit) {
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
