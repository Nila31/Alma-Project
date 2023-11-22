package com.proyecto.navegacionnilanavarro

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.proyecto.navegacionnilanavarro.Models.TicTacToeModel
import com.proyecto.navegacionnilanavarro.ViewModel.TicTacToeViewModel
import com.proyecto.navegacionnilanavarro.ui.theme.Indigo200
import kotlinx.coroutines.delay


@Composable
fun TicTacToeScreen(navController: NavController, viewModel: TicTacToeViewModel) {
    // Establece la imagen "flor" como fondo de pantalla
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.flor),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Contenido existente que se superpondrá a la imagen de fondo
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Tic-Tac-Toe",
                style = TextStyle(fontSize = 24.sp),
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (viewModel.playerPiece == null) {
                // Muestra la selección de ficha
                Text(
                    text = "Selecciona tu ficha:",
                    style = TextStyle(fontSize = 20.sp),
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    FichaSelector(ficha = "X", onSelectFicha = { viewModel.selectPlayerPiece(it) })
                    FichaSelector(ficha = "O", onSelectFicha = { viewModel.selectPlayerPiece(it) })
                }
            } else {
                // Muestra un cuadro de diálogo con las instrucciones y ciérralo después de 3 segundos
                var dialogVisible by remember { mutableStateOf(true) }

                LaunchedEffect(dialogVisible) {
                    delay(3000) // Espera 3 segundos
                    dialogVisible = false
                }

                if (dialogVisible) {
                    AlertDialog(
                        onDismissRequest = {},
                        title = { Text(text = "¡Comienza el juego!") },
                        text = {
                            Text(
                                text = "Para jugar, inicia la partida haciendo clic en el cuadro de tu elección.\n"
                            )
                        },
                        buttons = { } // Pasa una lista vacía de botones
                    )
                } else {
                    // Muestra el tablero y el juego
                    Board(
                        board = viewModel.gameState.value.board,
                        onCellClick = { index -> viewModel.onCellClick(index) },
                        winner = viewModel.gameState.value.winner
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    if (viewModel.gameState.value.winner != null) {
                        Text(
                            text = when (viewModel.gameState.value.winner) {
                                "Empate" -> "¡Empate!"
                                else -> "Ganador: ${viewModel.gameState.value.winner}"
                            },
                            style = TextStyle(fontSize = 20.sp),
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Box(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .clip(RoundedCornerShape(16.dp))
                                    .background(Indigo200)
                                    .size(120.dp)
                                    .clickable {
                                        // Restablece el estado del juego
                                        viewModel.gameState.value = TicTacToeModel()
                                    }
                            ) {
                                Text(
                                    "Repetir Partida",
                                    color = Color.Black,
                                    fontSize = 20.sp,
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .align(Alignment.Center)
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .clip(RoundedCornerShape(16.dp))
                                    .background(Indigo200)
                                    .size(120.dp)
                                    .clickable {
                                        navController.popBackStack()
                                    }
                            ) {
                                Text(
                                    "Salir",
                                    color = Color.Black,
                                    fontSize = 20.sp,
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .align(Alignment.Center)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

// Board.kt
@Composable
fun Board(board: List<String>, onCellClick: (Int) -> Unit, winner: String?) {
    Column {
        for (i in 0 until 3) {
            Row {
                for (j in 0 until 3) {
                    val index = i * 3 + j
                    Cell(value = board[index], onClick = { onCellClick(index) })
                }
            }
        }
    }
}

@Composable
fun Cell(value: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(64.dp)
            .border(1.dp, Color.Black)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = value,
            style = TextStyle(fontSize = 24.sp),
            color = Color.Black
        )
    }
}