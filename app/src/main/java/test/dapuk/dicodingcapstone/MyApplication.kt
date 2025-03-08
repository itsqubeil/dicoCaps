package test.dapuk.dicodingcapstone

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import test.dapuk.core.di.coreModule
import test.dapuk.dicodingcapstone.di.appModule

open class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(appModule)
            modules(coreModule)
        }
    }
}