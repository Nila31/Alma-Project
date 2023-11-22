package com.proyecto.navegacionnilanavarro

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.proyecto.navegacionnilanavarro.ui.theme.Mar
import com.proyecto.navegacionnilanavarro.ui.theme.Pink40
import com.proyecto.navegacionnilanavarro.ui.theme.PurpleGrey40
import com.proyecto.navegacionnilanavarro.ui.theme.PurpleGrey80

@Composable
fun BoringScreen(navController: NavController) {
    val painterBackground = painterResource(id = R.drawable.rojito)

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Fondo con imagen de plantas
        Image(
            painter = painterBackground,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
                .fillMaxHeight(),
            contentScale = ContentScale.Crop
        )

        // Contenido de la pantalla con desplazamiento vertical
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()) // Desplazamiento vertical
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Flecha blanca para retroceder en la esquina superior izquierda
            Image(
                painter = painterResource(id = R.drawable.anterior),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clickable { navController.popBackStack()  }
                    .align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Frase principal en un Card con esquinas ovaladas y color PurpleGrey80
            Card(
                modifier = Modifier
                    .background(Pink40, RoundedCornerShape(20.dp))
                    .padding(10.dp)
            ) {
                Text(
                    text = "\"¡Haz que cada día sea especial explorando nuevas emociones y desafiando tu mente!\"",
                    color = Color.Black,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(16.dp)
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            // Sección de Reflexión en Card con esquinas ovaladas y color PurpleGrey80
            Card(
                modifier = Modifier
                    .background(Pink40, RoundedCornerShape(20.dp))
                    .padding(10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Reflexión: En cada día, descubrimos la magia de ser parte de un mundo lleno de experiencias y aprendizajes. La edad no debería ser un obstáculo, sino una oportunidad para explorar nuevas emociones y desafiar nuestra mente. Sentirse parte activa de cada proceso nos conecta con la vitalidad de la vida, recordándonos que cada momento es valioso. ¡Haz que cada día sea especial, porque tu presencia es fundamental en este hermoso viaje!",
                        color = Color.Black,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Sección de Tips en Card con esquinas ovaladas y color PurpleGrey80
            Card(
                modifier = Modifier
                    .background(Pink40, RoundedCornerShape(20.dp))
                    .padding(10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Tips:",
                        color = Color.Black,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    // Actividades para personas de la tercera edad
                    Text(
                        text = "- Realiza paseos cortos al aire libre para mantenerse activo.",
                        color = Color.Black,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Text(
                        text = "- Encuentra algo que te apasione y te permita invertir tu tiempo de manera significativa.",
                        color = Color.Black,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Text(
                        text = "- Ejercita tu mente con juegos que pongan a prueba tu destreza mental y agilidad.",
                        color = Color.Black,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Sección de Recursos en Card con esquinas ovaladas y color blanco
            Card(
                modifier = Modifier
                    .background(Pink40, RoundedCornerShape(20.dp))
                    .padding(10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Explora:",
                        color = Color.Black,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    // Iconos de recursos (verde y azul, cuadrado ovalados)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        RecursoIcon(imageResource = R.drawable.cartas, title = "Coincidentes en el tablero.") {
                            navController.navigate("ColorMatchingGameInstructions")
                        }
                        RecursoIcon(imageResource = R.drawable.tic, title = "Tic-Tac-Toe:") {
                            navController.navigate("TicTacToeScreen")
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun RecursoIcon(imageResource: Int, title: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .width(150.dp)
            .clip(RoundedCornerShape(16.dp))
            .padding(4.dp)
            .clickable { onClick.invoke() }
    ) {
        // Imagen del recurso
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.Black.copy(alpha = 0.7f), RoundedCornerShape(8.dp))
                .padding(8.dp)
        )

        // Título encima de la imagen
        Text(
            text = title,
            color = Color.White,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}