package test.dapuk.core.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import test.dapuk.core.data.local.FavDatabase
import test.dapuk.core.data.remote.ApiConfig
import test.dapuk.core.data.repository.DevsRepository
import test.dapuk.core.domain.repository.IDevsRepository
import test.dapuk.core.domain.usecase.FavoriteUseCase
import test.dapuk.core.domain.usecase.GetDevsDetailUseCase
import test.dapuk.core.domain.usecase.GetDevsUseCase

val coreModule = module {
    single { FavDatabase.getInstance(androidContext()) }
    single { get<FavDatabase>().devsDao() }
    single { ApiConfig.getApiService() }
    single<IDevsRepository> { DevsRepository(get(), get()) }
    factory { GetDevsUseCase(get()) }
    factory { GetDevsDetailUseCase(get()) }
    factory { FavoriteUseCase(get()) }
}
