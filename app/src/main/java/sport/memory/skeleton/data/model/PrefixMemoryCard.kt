package sport.memory.skeleton.data.model

import androidx.annotation.DrawableRes

data class PrefixMemoryCard(
    val id: Int,
    val name: String,
    @field:DrawableRes val image: Int,
)
