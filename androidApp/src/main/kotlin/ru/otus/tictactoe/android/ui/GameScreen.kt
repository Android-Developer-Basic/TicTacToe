package ru.otus.tictactoe.android.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.otus.tictactoe.shared.Board
import ru.otus.tictactoe.shared.GameState
import ru.otus.tictactoe.shared.PLAYER_O
import ru.otus.tictactoe.shared.PLAYER_X
import ru.otus.tictactoe.shared.gameResult
import ru.otus.tictactoe.shared.isComplete
import ru.otus.tictactoe.shared.isFull

@Composable
fun GameScreen(gameState: GameState, onCellClick: (Int) -> Unit, onRestart: () -> Unit) {

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(20.dp))
        Text("Tic-Tac-Toe")

        Spacer(modifier = Modifier.height(20.dp))
        ButtonGrid(board = gameState.board, onclick = onCellClick)

        Spacer(modifier = Modifier.height(20.dp))
        ResetButton(onRestart)

        Spacer(modifier = Modifier.height(20.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text("Turn:")
            Text(gameState.nextPlayer)
        }

        if (gameState.board.isFull() || gameState.board.isComplete()) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(gameState.board.gameResult())
        }
    }
}

@Composable
fun ButtonGrid(board: Board, onclick: (Int) -> Unit) {
    Column(verticalArrangement = Arrangement.SpaceEvenly) {
        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            TicTacToeButton(text = board[0]) { onclick(0) }
            TicTacToeButton(text = board[1]) { onclick(1) }
            TicTacToeButton(text = board[2]) { onclick(2) }
        }

        Row(horizontalArrangement = Arrangement.SpaceAround) {
            TicTacToeButton(text = board[3]) { onclick(3) }
            TicTacToeButton(text = board[4]) { onclick(4) }
            TicTacToeButton(text = board[5]) { onclick(5) }
        }
        Row(horizontalArrangement = Arrangement.SpaceAround) {
            TicTacToeButton(text = board[6]) { onclick(6) }
            TicTacToeButton(text = board[7]) { onclick(7) }
            TicTacToeButton(text = board[8]) { onclick(8) }
        }
    }
}

@Composable
fun TicTacToeButton(text: String, onclick: () -> Unit) {
    Box(modifier = Modifier.padding(8.dp)) {
        TextButton(
            shape = MaterialTheme.shapes.medium,
            border = BorderStroke(1.dp, Color.DarkGray),
            onClick = onclick,
            enabled = text.isBlank()
        ) {
            Text(
                text = text,
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 35.sp
                ),
                modifier = Modifier
                    .padding(16.dp)
                    .size(40.dp)
                    .fillMaxHeight()
            )
        }
    }
}

@Composable
fun ResetButton(onClick: () -> Unit) {
    Button(onClick = onClick, modifier = Modifier
        .padding(16.dp)
        .height(50.dp)) {
        Text(
            text = "Restart",
            style = TextStyle(textAlign = TextAlign.Center),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable fun GameScreenPreview() {
    GameScreen(
        GameState(
            board = listOf(
                PLAYER_X, PLAYER_O, PLAYER_X,
                PLAYER_O, PLAYER_X, PLAYER_O,
                PLAYER_O, PLAYER_X, PLAYER_O
            ),
            nextPlayer = PLAYER_X
        ),
        { },
        { }
    )
}