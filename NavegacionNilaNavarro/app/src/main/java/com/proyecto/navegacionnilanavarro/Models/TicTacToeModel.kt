package com.proyecto.navegacionnilanavarro.Models

// TicTacToeModel.kt
data class TicTacToeModel(
    val board: List<String> = List(9) { "" },
    val currentPlayer: String = "X",
    val winner: String? = null
)