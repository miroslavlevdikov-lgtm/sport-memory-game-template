package sport.memory.skeleton

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import sport.memory.skeleton.di.dataModule
import sport.memory.skeleton.di.viewModule

class PrefixMemoryGameApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val appModules = dataModule + viewModule

        startKoin {
            androidLogger()
            androidContext(this@PrefixMemoryGameApplication)
            modules(appModules)
        }
    }
}