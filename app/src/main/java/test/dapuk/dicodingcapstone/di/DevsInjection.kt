package test.dapuk.dicodingcapstone.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import test.dapuk.dicodingcapstone.presentation.detail.DetailViewModel
import test.dapuk.dicodingcapstone.presentation.main.MainViewModel

val appModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { DetailViewModel(get(), get()) }

}
