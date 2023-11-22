package com.proyecto.navegacionnilanavarro.ViewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.proyecto.navegacionnilanavarro.Models.TicTacToeModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// TicTacToeViewModel.kt
// TicTacToeViewModel.kt
// TicTacToeViewModel.kt
class TicTacToeViewModel : ViewModel() {
    var gameState = mutableStateOf(TicTacToeModel())
    var playerPiece by mutableStateOf<String?>(null)
    var aiPiece by mutableStateOf<String?>(null)

    init {
        playerPiece = null
        aiPiece = null
    }

    fun selectPlayerPiece(piece: String) {
        playerPiece = piece
        aiPiece = if (piece == "X") "O" else "X"
        makePlayerMove()
    }

    fun makePlayerMove() {
        // Lógica del jugador para hacer un movimiento
        // Por ejemplo, mostrar una interfaz para que el jugador seleccione su movimiento
    }

    fun onCellClick(index: Int) {
        val currentPlayer = playerPiece
        val board = gameState.value.board.toMutableList()

        if (board[index].isEmpty() && gameState.value.winner == null && currentPlayer != null) {
            board[index] = currentPlayer
            gameState.value = gameState.value.copy(board = board, currentPlayer = aiPiece ?: "X")

            // Después de cada movimiento, verifica si hay un ganador o si el juego ha quedado en empate
            val winner = calculateWinner(board)
            if (winner != null) {
                gameState.value = gameState.value.copy(winner = winner)
            } else if (board.none { it.isEmpty() }) {
                gameState.value = gameState.value.copy(winner = "Empate")
            } else {
                makeAIMove()
            }
        }
    }

    fun makeAIMove() {
        val board = gameState.value.board.toMutableList()

        if (gameState.value.winner == null && gameState.value.currentPlayer == aiPiece) {
            CoroutineScope(Dispatchers.Default).launch {
                delay(3000)

                val emptyCells = board.indices.filter { board[it].isEmpty() }
                if (emptyCells.isNotEmpty()) {
                    val randomIndex = emptyCells.random()
                    board[randomIndex] = aiPiece ?: "O"
                    gameState.value = gameState.value.copy(board = board, currentPlayer = playerPiece ?: "X")

                    // Después del movimiento de la IA, verifica si hay un ganador o empate
                    val winner = calculateWinner(board)
                    if (winner != null) {
                        gameState.value = gameState.value.copy(winner = winner)
                    } else if (board.none { it.isEmpty() }) {
                        gameState.value = gameState.value.copy(winner = "Empate")
                    }
                }
            }
        }
    }

    private fun calculateWinner(board: List<String>): String? {
        val lines = listOf(
            listOf(0, 1, 2),
            listOf(3, 4, 5),
            listOf(6, 7, 8),
            listOf(0, 3, 6),
            listOf(1, 4, 7),
            listOf(2, 5, 8),
            listOf(0, 4, 8),
            listOf(2, 4, 6)
        )

        for (line in lines) {
            val (a, b, c) = line
            if (board[a].isNotEmpty() && board[a] == board[b] && board[a] == board[c]) {
                return board[a]
            }
        }

        if (board.all { it.isNotEmpty() }) {
            return "Empate"
        }

        return null
    }
}