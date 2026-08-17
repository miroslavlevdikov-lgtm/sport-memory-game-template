package sport.memory.skeleton.data.repository

import sport.memory.skeleton.data.model.PrefixMemoryCard

class PrefixMemoryCardRepository {
    //[@AGENT][Static source of the game deck. Fill the list with PrefixMemoryCard items, each holding a unique id, a display name, and an image drawable resource.]
    private val cards = listOf<PrefixMemoryCard>()

    fun getCards(): List<PrefixMemoryCard> = cards
}
