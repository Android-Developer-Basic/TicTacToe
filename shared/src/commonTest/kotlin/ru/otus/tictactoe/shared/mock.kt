package ru.otus.tictactoe.shared

internal val emptyBoard: Board = listOf(
    "", "", "",
    "", "", "",
    "", "", ""
)

internal val tieBoard: Board = listOf(
    PLAYER_X, PLAYER_O, PLAYER_X,
    PLAYER_O, PLAYER_X, PLAYER_O,
    PLAYER_O, PLAYER_X, PLAYER_O
)

internal val computerWinsBoard: Board = listOf(
    PLAYER_O, ""      , PLAYER_X,
    PLAYER_O, PLAYER_X,       "",
    "",       PLAYER_X, PLAYER_O
)

internal val playerWinsBoard: Board = listOf(
    PLAYER_X,       "", PLAYER_X,
    PLAYER_O, PLAYER_X,       "",
    PLAYER_O, PLAYER_X, PLAYER_O
)