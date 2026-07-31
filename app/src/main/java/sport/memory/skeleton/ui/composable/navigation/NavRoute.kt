package sport.memory.skeleton.ui.composable.navigation

import kotlinx.serialization.Serializable

sealed class NavRoute {
    @Serializable
    object Splash : NavRoute()

    @Serializable
    object MainMenu : NavRoute()

    @Serializable
    object About : NavRoute()

    @Serializable
    object Game : NavRoute()

    @Serializable
    data class GameOverScreen(val score: Int) : NavRoute()

    @Serializable
    object HighScores : NavRoute()

    @Serializable
    object Collection : NavRoute()
}