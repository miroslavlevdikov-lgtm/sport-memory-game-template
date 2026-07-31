package sport.memory.skeleton.ui.composable.sceens.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun PrefixSplashScreen(
    onMainMenuScreenNavigation: () -> Unit,
) {
    val iconScale = remember { Animatable(0f) }
    val iconRotation = remember { Animatable(15f) }

    val textAlpha = remember { Animatable(0f) }
    val textTranslationY = remember { Animatable(20f) }

    //[@AGENT][Splash screen component. Run parallel enter animations for icon scale/rotation and text alpha/vertical slide-in, then navigate to main menu after delay. Render app logo and title centered in a full-screen Box.]
}