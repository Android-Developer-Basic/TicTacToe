package ru.otus.tictactoe.shared

import kotlin.test.Test
import kotlin.test.assertEquals

internal class GameReducerKtTest {
    private val state = GameState()

    @Test
    fun blocksIncorrectIndices() {
        assertEquals(state, state.reduce(-1, PLAYER_X))
        assertEquals(state, state.reduce(9, PLAYER_X))
    }

    @Test
    fun blocksTurnIfNotPlayer() {
        assertEquals(state, state.reduce(4, PLAYER_O))
    }

    @Test
    fun blocksClicksOnTakenCells() {
        val playedState = state.copy(board = playerWinsBoard)
        assertEquals(playedState, playedState.reduce(0, PLAYER_X))
        assertEquals(playedState, playedState.reduce(0, PLAYER_O))
    }

    @Test
    fun updatesState() {
        assertEquals(
            GameState(
                board = listOf(
                    "",       "", "",
                    "", PLAYER_X, "",
                    "",       "", ""
                ),
                nextPlayer = PLAYER_O
            ),
            state.reduce(4, PLAYER_X)
        )
    }
}