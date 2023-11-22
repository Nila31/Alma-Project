package com.proyecto.navegacionnilanavarro

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
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
import com.proyecto.navegacionnilanavarro.ui.theme.Green700

import kotlinx.coroutines.delay


@Composable
fun ColorMatchingGame(navController: NavController) {
    var gameBoard by remember { mutableStateOf(createGameBoard()) }
    var selectedDrawable by remember { mutableStateOf("") }
    var score by remember { mutableStateOf(0) }
    var gameWon by remember { mutableStateOf(false) }
    var timeLeft by remember { mutableStateOf(60) }



    if (score == gameBoard.size * gameBoard[0].size) {
        gameWon = true
    }

    LaunchedEffect(timeLeft) {
        while (timeLeft > 0 && !gameWon) {
            delay(1000) // Wait for 1 second
            timeLeft--
        }
        if (!gameWon) {
            // The player has lost
            gameWon = true
        }
    }

    val painterBackground = painterResource(id = R.drawable.suelo)

    Image(
        painter = painterBackground,
        contentDescription = null,
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .fillMaxHeight(),
        contentScale = ContentScale.Crop // Ajusta el contentScale según tus necesidades
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),  // Habilita el desplazamiento vertical
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (gameWon) {
                if (score == gameBoard.size * gameBoard[0].size) {
                    Text("¡Ganaste! Puntaje: $score", modifier = Modifier.padding(16.dp), fontSize = 24.sp, color = Color.White)
                } else {
                    Text("¡Perdiste! Puntaje: $score", modifier = Modifier.padding(16.dp), fontSize = 24.sp , color = Color.White)
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Box(
                        modifier = Modifier
                            .padding(8.dp)
                            .size(150.dp)
                            .background(Green700, RoundedCornerShape(24.dp))
                            .clickable {
                                gameBoard = createGameBoard()
                                selectedDrawable = ""
                                score = 0
                                gameWon = false
                                timeLeft = 60 // Reinicia el temporizador
                            }
                    ) {
                        Text("Repetir Partida", color = Color.White, fontSize = 24.sp, modifier = Modifier.padding(16.dp))
                    }

                    // Botón "Salir"
                    Box(
                        modifier = Modifier
                            .padding(3.dp) // Espaciado
                            .size(150.dp)
                            .background(Green700, RoundedCornerShape(24.dp))
                            .clickable { navController.popBackStack() }
                    ) {
                        Text("Salir", color = Color.White, fontSize = 24.sp, modifier = Modifier.padding(16.dp))
                    }

                }
            } else {
                Text("Tiempo restante: $timeLeft segundos", modifier = Modifier.padding(16.dp), fontSize = 24.sp,color = Color.White)
                Text("Puntaje: $score", modifier = Modifier.padding(16.dp), fontSize = 24.sp,color = Color.White,
                )

                for (rowIndex in gameBoard.indices) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        for (colIndex in gameBoard[rowIndex].indices) {
                            DrawableTile(
                                drawableName = gameBoard[rowIndex][colIndex],
                                onTileClicked = { clickedDrawable ->
                                    if (!gameWon && clickedDrawable == selectedDrawable) {
                                        score++
                                        gameBoard = updateGameBoard(gameBoard, "", rowIndex, colIndex)
                                    }
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                DrawablePicker(selectedDrawable = selectedDrawable) { newDrawable ->
                    if (!gameWon) {
                        selectedDrawable = newDrawable
                    }
                }
            }
        }
    }
}

@Composable
fun DrawableTile(drawableName: String, onTileClicked: (String) -> Unit) {
    Box(
        modifier = Modifier
            .size(60.dp)
            .clip(shape = MaterialTheme.shapes.medium)
            .clickable { onTileClicked(drawableName) }
    ) {
        if (drawableName.isNotEmpty()) {
            Image(
                painter = painterResource(id = getResourceId(drawableName)),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun DrawablePicker(selectedDrawable: String, onDrawableSelected: (String) -> Unit) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "Selecciona el vegetal:",
            color = Color.White,
            fontSize = 24.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally) // No es necesario especificar la alineación vertical
                .padding(8.dp)
        )

        val drawableOptions = listOf("tomate", "cebolla", "lechuga", "patata", "chile")

        Row(
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            drawableOptions.forEach { drawableName ->
                DrawableOption(drawableName, selectedDrawable, onDrawableSelected)
            }
        }
    }
}

@Composable
fun DrawableOption(
    drawableName: String,
    selectedDrawable: String,
    onDrawableSelected: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(shape = MaterialTheme.shapes.medium)
            .clickable { onDrawableSelected(drawableName) }
            .padding(4.dp)
            .border(
                width = 2.dp,
                color = if (drawableName == selectedDrawable) Color.Black else Color.Transparent,
                shape = MaterialTheme.shapes.medium
            )
    ) {
        if (drawableName.isNotEmpty()) {
            Image(
                painter = painterResource(id = getResourceId(drawableName)),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center) // Centra la imagen vertical y horizontalmente
            )
        }
    }
}

fun getResourceId(drawableName: String): Int {
    // Asigna un recurso de ID de acuerdo al nombre del drawable.
    return when (drawableName) {
        "tomate" -> R.drawable.tomate
        "cebolla" -> R.drawable.cebolla
        "lechuga" -> R.drawable.lechuga
        "patata" -> R.drawable.patata
        "chile" -> R.drawable.chile
        else -> 0 // 0 representa ninguna imagen
    }
}

fun createGameBoard(): List<List<String>> {
    val numRows = 4
    val numCols = 4
    val drawables = listOf("tomate", "cebolla", "lechuga", "patata", "chile")
    val board = List(numRows) { List(numCols) { drawables.random() } }
    return board
}

fun updateGameBoard(
    gameBoard: List<List<String>>,
    targetDrawable: String,
    row: Int,
    col: Int
): List<List<String>> {
    return gameBoard.mapIndexed { rowIndex, rowList ->
        if (rowIndex == row) {
            rowList.mapIndexed { colIndex, drawable ->
                if (colIndex == col) {
                    targetDrawable
                } else {
                    drawable
                }
            }
        } else {
            rowList
        }
    }
}

@Composable
fun ColorMatchingGameInstructions() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "¡Bienvenido al Juego de Coincidencia de Colores!",
            color = Color.White,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "Instrucciones:",
            color = Color.White,
            fontSize = 28.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "1. Selecciona los vegetales coincidentes en el tablero.",
            color = Color.White,
            fontSize = 28.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "2. Cada acierto aumentará tu puntaje.",
            color = Color.White,
            fontSize = 28.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "3. El juego termina cuando se completa el tablero o se agota el tiempo.",
            color = Color.White,
            fontSize = 28.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Button(
            onClick = { /* Puedes navegar de regreso al juego desde aquí */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(Green700)
        ) {
            Text("Volver al Juego", color = Color.White, fontSize = 24.sp)
        }
    }
}