package ru.otus.tictactoe.shared

import ru.otus.tictactoe.shared.computer
import ru.otus.tictactoe.shared.isFull
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class GameUtilsKtTest {
    @Test
    fun boardFullIfFull() {
        assertTrue { tieBoard.isFull() }
    }

    @Test
    fun boardNotFullIfNotFull() {
        assertFalse { emptyBoard.isFull() }
    }

    @Test
    fun computerWinsIfPossible() {
        assertEquals(6, computerWinsBoard.computer())
    }

    @Test
    fun computerBlocksIfPossible() {
        assertEquals(1, playerWinsBoard.computer())
    }
}