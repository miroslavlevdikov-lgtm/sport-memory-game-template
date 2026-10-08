package sport.memory.skeleton.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import sport.memory.skeleton.R
import sport.memory.skeleton.data.entity.PrefixHighScore
import sport.memory.skeleton.data.model.PrefixMemoryCard
import sport.memory.skeleton.data.repository.PrefixHighScoreRepository
import sport.memory.skeleton.data.repository.PrefixMemoryCardRepository
import sport.memory.skeleton.di.DispatcherProvider
import sport.memory.skeleton.ui.state.DisplayedContentState
import sport.memory.skeleton.ui.state.MemoryGameUiState
import java.time.LocalDateTime

class PrefixGameViewModel(
    private val memoryCardRepository: PrefixMemoryCardRepository,
    private val highScoreRepository: PrefixHighScoreRepository,
    private val dispatchers: DispatcherProvider,
) : ViewModel() {

    private val _uiState = MutableStateFlow(MemoryGameUiState())
    val uiState: StateFlow<MemoryGameUiState> = _uiState.asStateFlow()

    private var sequence: List<PrefixMemoryCard> = emptyList()
    private var userStepIndex = 0

    private var gameJob: Job? = null

    init {
        startNewGame()
    }

    fun startNewGame() {
        gameJob?.cancel()
        gameJob = viewModelScope.launch(dispatchers.main) {
            _uiState.update {
                MemoryGameUiState(isLoading = true)
            }

            val availableCards = memoryCardRepository.getCards().shuffled().take(5)

            _uiState.update {
                it.copy(
                    deck = availableCards,
                    isLoading = false,
                    currentScore = 0,
                    isGameOver = false,
                    displayedContent = DisplayedContentState.NoContent
                )
            }

            startNewRound()
        }
    }

    private suspend fun startNewRound() {
        userStepIndex = 0
        val targetLength = _uiState.value.currentScore + 1

        sequence = List(targetLength) { _uiState.value.deck.random() }

        _uiState.update {
            it.copy(
                isShowingSequence = true,
                displayedContent = DisplayedContentState.StageMessage(R.string.preparation_ready)
            )
        }
        delay(1200)

        for ((index, card) in sequence.withIndex()) {
            val cardNumber = _uiState.value.deck.indexOfFirst { it.id == card.id } + 1

            _uiState.update {
                it.copy(
                    displayedContent = DisplayedContentState.CombinationElement(
                        stepIndex = index,
                        cardNumber = cardNumber,
                        card = card
                    )
                )
            }
            delay(1000)
        }

        _uiState.update {
            it.copy(
                isShowingSequence = false,
                displayedContent = DisplayedContentState.NoContent
            )
        }
    }

    fun onCardClicked(card: PrefixMemoryCard) {
        val currentState = _uiState.value

        if (currentState.isShowingSequence || currentState.isGameOver || currentState.isLoading) return

        if (gameJob?.isActive == true) return

        gameJob = viewModelScope.launch(dispatchers.main) {
            val cardNumber = currentState.deck.indexOfFirst { it.id == card.id } + 1

            _uiState.update {
                it.copy(
                    displayedContent = DisplayedContentState.CombinationElement(
                        stepIndex = userStepIndex,
                        cardNumber = cardNumber,
                        card = card
                    )
                )
            }

            val expectedCard = sequence.getOrNull(userStepIndex)

            if (expectedCard?.id == card.id) {
                userStepIndex++

                if (userStepIndex == sequence.size) {
                    val newScore = sequence.size
                    _uiState.update { it.copy(currentScore = newScore) }
                    delay(500)
                    startNewRound()
                }
            } else {
                handleGameOver()
            }
        }
    }

    private suspend fun handleGameOver() {
        val finalScore = _uiState.value.currentScore

        _uiState.update {
            it.copy(
                isGameOver = true,
                displayedContent = DisplayedContentState.NoContent
            )
        }

        if (finalScore > 0) {
            highScoreRepository.saveIfBetter(
                PrefixHighScore(
                    score = finalScore,
                    timestamp = LocalDateTime.now()
                )
            )
        }
    }
}