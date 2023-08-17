package ru.otus.tictactoe.shared

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import ru.otus.tictactoe.shared.GameModel.Companion.COMPUTER_THINKS_MS
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
internal class GameModelTest {
    @Test
    fun createsInitialStateAtStart() = runTest {
        val model = GameModel(this)
        val state = model.state.first()
        assertTrue {
            state.board.all { "" == it }
        }
        assertEquals(
            PLAYER_X,
            state.nextPlayer
        )
    }

    @Test
    fun updatesOnPlayerTurn() = runTest(StandardTestDispatcher()) {
        val model = GameModel(this)
        val values = mutableListOf<GameState>()
        val collectJob = launch {
            model.state.toList(values)
        }
        testScheduler.advanceUntilIdle()

        model.processCellClick(0)
        testScheduler.advanceUntilIdle()

        assertEquals(
            PLAYER_X,
            values[1].board[0]
        )
        assertEquals(
            PLAYER_O,
            values[1].nextPlayer
        )

        collectJob.cancel()
    }

    @Test
    fun computerRespondsAfterSomeInterval() = runTest(StandardTestDispatcher()) {
        val model = GameModel(this)
        val values = mutableListOf<GameState>()
        val collectJob = launch() {
            model.state.toList(values)
        }
        testScheduler.advanceUntilIdle()

        model.processCellClick(0)
        testScheduler.runCurrent()

        assertEquals(
            PLAYER_X,
            values[1].board[0]
        )
        assertEquals(
            PLAYER_O,
            values[1].nextPlayer
        )
        assertFalse {
            values[1].board.any { PLAYER_O == it }
        }

        testScheduler.advanceTimeBy(COMPUTER_THINKS_MS + 1)
        testScheduler.runCurrent()

        assertTrue {
            values[2].board.any { PLAYER_O == it }
        }

        collectJob.cancel()
    }

    @Test
    fun restartsGame() = runTest(UnconfinedTestDispatcher()) {
        val model = GameModel(this)
        val values = mutableListOf<GameState>()
        val collectJob = launch() {
            model.state.toList(values)
        }

        model.processCellClick(0)

        assertTrue {
            values[1].board.any { PLAYER_X == it }
        }

        model.restart()

        assertTrue {
            values[2].board.all { "" == it }
        }

        collectJob.cancel()
    }
}