package ru.otus.tictactoe.shared

/**
 * Creates new game state on cell click
 */
fun GameState.reduce(index: Int, player: String): GameState {
    // Check index
    if (index !in (0..8)) {
        return this
    }
    // Check player has it's turn
    if (player != nextPlayer) {
        return this
    }
    // Check if cell is already taken
    if ("" != board[index]) {
        return this
    }

    return GameState(
        board.copy().toMutableList().apply { this[index] = player },
        if (PLAYER_X == player) PLAYER_O else PLAYER_X
    )
}