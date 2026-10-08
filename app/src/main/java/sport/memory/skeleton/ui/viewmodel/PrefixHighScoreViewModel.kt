package sport.memory.skeleton.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import sport.memory.skeleton.data.entity.PrefixHighScore
import sport.memory.skeleton.data.repository.PrefixHighScoreRepository
import sport.memory.skeleton.di.DispatcherProvider

class PrefixHighScoreViewModel(
    private val highScoreRepository: PrefixHighScoreRepository,
    private val dispatchers: DispatcherProvider,
) : ViewModel() {

    private val _highScoresState = MutableStateFlow<List<PrefixHighScore>>(emptyList())
    val highScoresState: StateFlow<List<PrefixHighScore>> = _highScoresState.asStateFlow()

    private val _isLoadingState = MutableStateFlow(true)
    val isLoadingState: StateFlow<Boolean> = _isLoadingState.asStateFlow()

    init {
        loadHighScores()
    }

    fun loadHighScores() {
        viewModelScope.launch(dispatchers.main) {
            _isLoadingState.value = true
            _highScoresState.value = highScoreRepository.getAll()
            _isLoadingState.value = false
        }
    }

    fun deleteHighScores() {
        viewModelScope.launch(dispatchers.main) {
            highScoreRepository.deleteAll()
            _highScoresState.value = emptyList()
        }
    }
}