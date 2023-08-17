package ru.otus.tictactoe.shared

/**
 * Game state
 */
data class GameState(val board: Board = EMPTY_BOARD, val nextPlayer: String = PLAYER_X)

/**
 * Empty board
 */
private val EMPTY_BOARD = listOf(
    "", "", "",
    "", "", "",
    "", "", ""
)
