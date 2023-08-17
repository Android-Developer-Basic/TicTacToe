package ru.otus.tictactoe.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.StateFlow
import ru.otus.tictactoe.shared.GameModel
import ru.otus.tictactoe.shared.GameState

/**
 * Wraps common model to inject to Android
 */
class MainActivityViewModel : ViewModel() {

    /**
     * Shared model - common for all apps
     */
    private val gameModel = GameModel(viewModelScope)

    /**
     * View state
     */
    val state: StateFlow<GameState> get() = gameModel.state

    /**
     * Processes user input
     */
    fun processCellClick(id: Int) {
        gameModel.processCellClick(id)
    }

    /**
     * Restarts game
     */
    fun restart() {
        gameModel.restart()
    }
}