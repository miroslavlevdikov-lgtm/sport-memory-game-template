package sport.memory.skeleton.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import sport.memory.skeleton.ui.viewmodel.PrefixGameViewModel
import sport.memory.skeleton.ui.viewmodel.PrefixHighScoreViewModel

val viewModule = module {
    viewModel {
        PrefixGameViewModel(
            memoryCardRepository = get(),
            highScoreRepository = get()
        )
    }

    viewModel {
        PrefixHighScoreViewModel(
            highScoreRepository = get()
        )
    }
}