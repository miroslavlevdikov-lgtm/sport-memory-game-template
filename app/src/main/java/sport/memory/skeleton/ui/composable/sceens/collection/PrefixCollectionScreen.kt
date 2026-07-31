package sport.memory.skeleton.ui.composable.sceens.collection

import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import org.koin.compose.koinInject
import sport.memory.skeleton.data.repository.PrefixMemoryCardRepository

@Composable
fun PrefixCollectionScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
) {
    val repository: PrefixMemoryCardRepository = koinInject()
    val cards = remember { repository.getCards() }
    val pagerState = rememberPagerState(
        pageCount = { cards.size }
    )

    //[@AGENT][Collection screen. Display AppTopBar with back action, and render a HorizontalPager for cards with scale/alpha scroll animations, presenting each item's main image and description dossier.]
}