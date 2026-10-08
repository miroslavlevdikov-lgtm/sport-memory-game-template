package sport.memory.skeleton.data.repository

import kotlinx.coroutines.withContext
import sport.memory.skeleton.data.database.dao.PrefixHighScoreDao
import sport.memory.skeleton.data.entity.PrefixHighScore
import sport.memory.skeleton.di.DispatcherProvider

class PrefixHighScoreRepository(
    private val highScoreDao: PrefixHighScoreDao,
    private val dispatchers: DispatcherProvider,
) {
    suspend fun saveIfBetter(score: PrefixHighScore) {
        withContext(dispatchers.io) {
            highScoreDao.saveIfBetter(score)
        }
    }

    suspend fun getAll(): List<PrefixHighScore> {
        return withContext(dispatchers.io) {
            highScoreDao.getAll()
        }
    }

    suspend fun deleteAll() {
        withContext(dispatchers.io) {
            highScoreDao.deleteAll()
        }
    }
}
