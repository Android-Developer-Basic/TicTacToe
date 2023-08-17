import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import ru.otus.tictactoe.shared.GameModel
import ru.otus.tictactoe.shared.ui.GameScreen

fun main() = application {
    val windowState = rememberWindowState(
        width = 500.dp, height = 700.dp
    )
    val viewModel = GameModel(rememberCoroutineScope())

    Window(
        onCloseRequest = ::exitApplication,
        resizable = false,
        title = "Tic-Tac-Toe",
        state = windowState
    ) {
        val viewState by viewModel.state.collectAsState()
        GameScreen(
            gameState = viewState,
            onCellClick = { viewModel.processCellClick(it) },
            onRestart = { viewModel.restart() }
        )
    }
}