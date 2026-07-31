package sport.memory.skeleton.ui.state

import sport.memory.skeleton.data.model.PrefixMemoryCard

data class MemoryGameUiState(
    val deck: List<PrefixMemoryCard> = emptyList(),
    val currentScore: Int = 0,
    val isLoading: Boolean = false,
    val isShowingSequence: Boolean = false,
    val isGameOver: Boolean = false,
    val displayedContent: DisplayedContentState = DisplayedContentState.NoContent
)
