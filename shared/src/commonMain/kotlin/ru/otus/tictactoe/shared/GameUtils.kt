package ru.otus.tictactoe.shared

/**
 * Human
 */
const val PLAYER_X = "X"

/**
 * Computer
 */
const val PLAYER_O = "O"

/**
 * Checks if board is full and there are no more turns
 */
internal fun Board.isFull(): Boolean = all { it == PLAYER_X || it == PLAYER_O }

/**
 * Copies board to the new instance
 */
internal fun Board.copy(): Board = map { it }

/**
 * Choose random move from give
 */
private fun Board.chooseRandomMove(moves: Set<Int>): Int {
    val possible = mutableListOf<Int>()
    moves.forEach {
        if ("" == this[it]) {
            possible.add(it)
        }
    }
    return when {
        possible.isEmpty() -> -1
        else -> possible.random()
    }
}

/**
 * Computer AI
 */
internal fun Board.computer(): Int {
    // Check if computer wins
    val computerWins = getWinningMove(PLAYER_O)
    if (computerWins != -1) return computerWins

    // Check if player wins the next move and block
    val playerWins = getWinningMove(PLAYER_X)
    if (playerWins != -1) return playerWins

    // Try corners
    val move = chooseRandomMove(setOf(0, 2, 6, 8))
    if (move != -1) return move

    // Try center
    if (this[4] == "") return 4

    // Try sides
    return chooseRandomMove(setOf(1, 3, 5, 7))
}

/**
 * Searches for winning move
 * @return Winning move or -1
 */
private fun Board.getWinningMove(player: String): Int {
    for (i in indices) {
        val attempt = copy().toMutableList()
        if (attempt[i] == "") {
            attempt[i] = player
            if (attempt.isGameWon(player)) {
                return i
            }
        }
    }
    return -1
}

internal fun Board.isGameWon(player: String): Boolean = when {
    // Rows
    this[0] == player && this[1] == player && this[2] == player -> true
    this[3] == player && this[4] == player && this[5] == player -> true
    this[6] == player && this[7] == player && this[8] == player -> true

    // Columns
    this[0] == player && this[3] == player && this[6] == player -> true
    this[1] == player && this[4] == player && this[7] == player -> true
    this[2] == player && this[5] == player && this[8] == player -> true

    // Diagonals
    this[2] == player && this[4] == player && this[6] == player -> true
    this[0] == player && this[4] == player && this[8] == player -> true

    else -> false
}

/**
 * Human-readable game result
 */
internal fun Board.gameResult(): String = when {
    isGameWon(PLAYER_X) -> "You Won!"
    isGameWon(PLAYER_O) -> "COMPUTER Won!"
    else -> "Tie"
}


