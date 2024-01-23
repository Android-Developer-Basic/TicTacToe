import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeUIViewController
import ru.otus.tictactoe.shared.GameModel
import ru.otus.tictactoe.shared.ui.GameScreen

fun MainViewController() = ComposeUIViewController { App() }

@Composable
fun App() {
    val viewModel = GameModel(rememberCoroutineScope())

    MaterialTheme {
        val viewState by viewModel.state.collectAsState()
        GameScreen(
            gameState = viewState,
            onCellClick = { viewModel.processCellClick(it) },
            onRestart = { viewModel.restart() }
        )
    }
}