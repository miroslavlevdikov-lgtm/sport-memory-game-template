package sport.memory.skeleton.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity("high_scores")
data class PrefixHighScore(
    @PrimaryKey val score: Int,
    val timestamp: LocalDateTime,
)