package sport.memory.skeleton.ui.state

import androidx.annotation.StringRes
import sport.memory.skeleton.data.model.PrefixMemoryCard

sealed interface DisplayedContentState {
    data class CombinationElement(
        val stepIndex: Int,
        val cardNumber: Int,
        val card: PrefixMemoryCard
    ) : DisplayedContentState

    data class StageMessage(@field:StringRes val message: Int) : DisplayedContentState
    data object NoContent : DisplayedContentState
}