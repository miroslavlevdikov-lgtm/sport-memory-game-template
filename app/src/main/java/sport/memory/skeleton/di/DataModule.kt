package sport.memory.skeleton.di

import androidx.room.Room
import org.koin.dsl.module
import sport.memory.skeleton.data.database.PrefixDatabase
import sport.memory.skeleton.data.repository.PrefixHighScoreRepository
import sport.memory.skeleton.data.repository.PrefixMemoryCardRepository

private const val Prefix_DB_NAME = "prefix_db"

val dataModule = module {
    single {
        Room.databaseBuilder(
            context = get(),
            klass = PrefixDatabase::class.java,
            name = Prefix_DB_NAME
        ).build()
    }

    single { get<PrefixDatabase>().highScoreDao() }

    single { PrefixHighScoreRepository(highScoreDao = get()) }

    single { PrefixMemoryCardRepository() }
}