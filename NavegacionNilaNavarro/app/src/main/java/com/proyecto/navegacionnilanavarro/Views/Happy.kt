package com.proyecto.navegacionnilanavarro.Views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.proyecto.navegacionnilanavarro.R

@Composable
fun Happy(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFFEB3B), shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()), // Agrega el scroll vertical
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate("WelcomeScreen")
                    }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Atrás",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(42.dp)
                        .padding(8.dp)
                )
            }
            Spacer(modifier = Modifier.height(60.dp))
            Text(
                text = "¡Disfruta tu día!",
                style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.Bold),
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.happy_elderly),
                contentDescription = "Persona mayor feliz",
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .background(color = Color.White)
            )

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Aprovecha al máximo tu día. Aquí tienes algunas recomendaciones para mantener tu bienestar activo:",
                style = MaterialTheme.typography.body1,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(40.dp))

            // Lista de recomendaciones
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                RecommendationItem(
                    imageResId = R.drawable.favorite,
                    text = "Mantén una actitud positiva.",
                )
                RecommendationItem(
                    imageResId = R.drawable.localflorist,
                    text = "Disfruta del aire libre y la naturaleza.",
                )
                RecommendationItem(
                    imageResId = R.drawable.group,
                    text = "Socializa con amigos y familiares.",
                )
                RecommendationItem(
                    imageResId = R.drawable.localactivity,
                    text = "Participa en actividades recreativas.",
                )
            }

            Spacer(modifier = Modifier.height(16.dp))


        }
    }
}

@Composable
fun RecommendationItem(imageResId: Int, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text, style = MaterialTheme.typography.body1, color = Color.Black)
    }
}