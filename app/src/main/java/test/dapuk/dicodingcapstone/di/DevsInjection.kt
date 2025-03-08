package test.dapuk.dicodingcapstone.di

import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import test.dapuk.core.data.local.FavDatabase
import test.dapuk.core.data.remote.ApiConfig
import test.dapuk.core.data.repository.DevsRepository
import test.dapuk.core.domain.repository.IDevsRepository
import test.dapuk.core.domain.usecase.FavoriteUseCase
import test.dapuk.core.domain.usecase.GetDevsDetailUseCase
import test.dapuk.core.domain.usecase.GetDevsUseCase
import test.dapuk.dicodingcapstone.presentation.detail.DetailViewModel
import test.dapuk.dicodingcapstone.presentation.main.MainViewModel

val appModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { DetailViewModel(get(), get()) }

}
