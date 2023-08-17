package ru.otus.tictactoe.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import ru.otus.tictactoe.shared.ui.GameScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: MainActivityViewModel by viewModels()
            val viewState by viewModel.state.collectAsState()
            GameScreen(
                gameState = viewState,
                onCellClick = { viewModel.processCellClick(it) },
                onRestart = { viewModel.restart() }
            )
        }
    }
}
