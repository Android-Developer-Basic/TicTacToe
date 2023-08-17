package ru.otus.tictactoe.shared

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Runs game and saves state
 */
class GameModel(private val scope: CoroutineScope) {
    /**
     * Stores current game state
     */
    private val mState = MutableStateFlow(GameState())

    /**
     * Exports state to the outside word in read-only manner
     */
    val state: StateFlow<GameState> get() = mState.asStateFlow()

    /**
     * Computer is "thinking"... We cancel this work on restart
     */
    private var computersTurn: Job? = null

    /**
     * Processes user input
     */
    fun processCellClick(id: Int) {
        val currentState = mState.value

        // Check if player's turn
        if (PLAYER_X != currentState.nextPlayer) {
            return
        }

        // Try to record a move from the human player
        val newState = currentState.reduce(id, PLAYER_X)

        mState.value = newState
        computersTurn = scope.launch {
            delay(COMPUTER_THINKS_MS)
            mState.value = newState.reduce(newState.board.computer(), PLAYER_O)
        }
    }

    /**
     * Restarts game
     */
    fun restart() {
        computersTurn?.cancel()
        mState.value = GameState()
    }

    companion object {
        const val COMPUTER_THINKS_MS = 1000L
    }
}