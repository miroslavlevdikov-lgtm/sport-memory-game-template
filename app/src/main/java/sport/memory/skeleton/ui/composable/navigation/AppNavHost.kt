package sport.memory.skeleton.ui.composable.navigation

import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import sport.memory.skeleton.ui.composable.sceens.about.PrefixAboutScreen
import sport.memory.skeleton.ui.composable.sceens.collection.PrefixCollectionScreen
import sport.memory.skeleton.ui.composable.sceens.game.PrefixGameScreen
import sport.memory.skeleton.ui.composable.sceens.game_over.PrefixGameOverScreen
import sport.memory.skeleton.ui.composable.sceens.high_scores.PrefixHighScoresScreen
import sport.memory.skeleton.ui.composable.sceens.main_menu.PrefixMainMenuScreen
import sport.memory.skeleton.ui.composable.sceens.splash.PrefixSplashScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val activity = LocalActivity.current

    NavHost(
        navController = navController,
        startDestination = NavRoute.Splash,
        modifier = modifier,
    ) {
        composable<NavRoute.Splash> {
            PrefixSplashScreen(
                onMainMenuScreenNavigation = {
                    navController.navigate(route = NavRoute.MainMenu) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable<NavRoute.MainMenu> {
            PrefixMainMenuScreen(
                onGameScreenNavigation = { navController.navigate(route = NavRoute.Game) },
                onAboutScreenNavigation = { navController.navigate(route = NavRoute.About) },
                onHighScoresScreenNavigation = { navController.navigate(route = NavRoute.HighScores) },
                onCollectionScreenNavigation = { navController.navigate(route = NavRoute.Collection) },
                onExit = { activity?.finishAffinity() }
            )
        }

        composable<NavRoute.Game> {
            PrefixGameScreen(
                onGameOverNavigation = { result ->
                    navController.navigate(route = NavRoute.GameOverScreen(score = result)) {
                        popUpTo(NavRoute.MainMenu) {
                            inclusive = false
                        }
                    }
                },
                onBackClick = { navController.popBackStack() }
            )
        }

        composable<NavRoute.GameOverScreen> { backStackEntry ->
            val gameOverRoute: NavRoute.GameOverScreen = backStackEntry.toRoute()
            PrefixGameOverScreen(
                score = gameOverRoute.score,
                onPlayAgain = {
                    navController.navigate(route = NavRoute.Game) {
                        popUpTo(NavRoute.MainMenu) {
                            inclusive = false
                        }
                    }
                },
                onMainMenu = {
                    navController.navigate(route = NavRoute.MainMenu) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable<NavRoute.About> {
            PrefixAboutScreen(onBackClick = { navController.popBackStack() })
        }

        composable<NavRoute.HighScores> {
            PrefixHighScoresScreen(onBackClick = { navController.popBackStack() })
        }

        composable<NavRoute.Collection> {
            PrefixCollectionScreen(onBackClick = { navController.popBackStack() })
        }
    }
}