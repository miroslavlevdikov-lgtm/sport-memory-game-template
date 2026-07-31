package sport.memory.skeleton.data.repository

import sport.memory.skeleton.R
import sport.memory.skeleton.data.model.PrefixMemoryCard

class PrefixMemoryCardRepository {
    private val cards = listOf<PrefixMemoryCard>(
        PrefixMemoryCard(
            id = 2,
            name = "Cristiano Ronaldo",
            shortDescription = "Portuguese forward known for his incredible goalscoring and athleticism. A five-time Ballon d'Or winner and Euro 2016 champion.",
            image = R.drawable.ronaldo
        ),
        PrefixMemoryCard(
            id = 3,
            name = "Ronaldinho",
            shortDescription = "Brazilian star famous for his incredible dribbling, skills, and entertaining style. He won the Ballon d'Or in 2005.",
            image = R.drawable.ronaldinho
        ),
        PrefixMemoryCard(
            id = 4,
            name = "Ronaldo Nazário",
            shortDescription = "Brazilian striker widely regarded as one of the greatest forwards ever. A two-time Ballon d'Or winner and two-time World Cup champion.",
            image = R.drawable.ronaldo_nazario
        ),
        PrefixMemoryCard(
            id = 5,
            name = "Kylian Mbappé",
            shortDescription = "French forward known for his explosive speed and finishing. He won the 2018 FIFA World Cup with France.",
            image = R.drawable.mbappe
        ),
        PrefixMemoryCard(
            id = 6,
            name = "Lionel Messi",
            shortDescription = "Argentine forward and one of the greatest players in football history. He won the 2022 FIFA World Cup and multiple Ballon d'Or awards.",
            image = R.drawable.messi
        ),
        PrefixMemoryCard(
            id = 7,
            name = "Diego Maradona",
            shortDescription = "Argentine legend renowned for his extraordinary technique and ball control. He led Argentina to victory at the 1986 FIFA World Cup.",
            image = R.drawable.maradona
        ),
        PrefixMemoryCard(
            id = 8,
            name = "Neymar",
            shortDescription = "Brazilian attacker famous for his dribbling, creativity, and technical ability. He has played for Santos, Barcelona, and Paris Saint-Germain.",
            image = R.drawable.neymar
        ),
        PrefixMemoryCard(
            id = 9,
            name = "Zinedine Zidane",
            shortDescription = "French midfielder known for his elegance, technique, and vision. He won the 1998 FIFA World Cup and later became a successful Real Madrid manager.",
            image = R.drawable.zidane
        ),
        PrefixMemoryCard(
            id = 10,
            name = "Pelé",
            shortDescription = "Brazilian football legend and the only player to win three FIFA World Cups. He became one of the most iconic figures in football history.",
            image = R.drawable.pele
        )
    )

    fun getCards(): List<PrefixMemoryCard> = cards
}