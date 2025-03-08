package test.dapuk.favorie.favorite

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val FavoriteModule  = module {
    viewModel { FavoriteViewModel(get()) }
}