package sport.memory.skeleton.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import sport.memory.skeleton.data.entity.PrefixHighScore

@Dao
interface PrefixHighScoreDao {
    @Query("SELECT * FROM high_scores ORDER BY score DESC LIMIT 1")
    suspend fun getBestScore(): PrefixHighScore?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(score: PrefixHighScore)

    @Transaction
    suspend fun saveIfBetter(newScore: PrefixHighScore) {
        val currentBest = getBestScore()
        if (currentBest == null || newScore.score > currentBest.score) {
            insert(newScore)
        }
    }

    @Query("SELECT * FROM high_scores ORDER BY score DESC")
    suspend fun getAll(): List<PrefixHighScore>

    @Query("DELETE FROM high_scores")
    suspend fun deleteAll()
}