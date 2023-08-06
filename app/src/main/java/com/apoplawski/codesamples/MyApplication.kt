package com.apoplawski.codesamples

import android.app.Application
import com.apoplawski.codesamples.di.appModule
import com.apoplawski.codesamples.di.searchModule
import com.apoplawski.codesamples.articles.permissions.CurrentActivityProvider
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val activityProvider = CurrentActivityProvider(application = this@MyApplication).apply {
            initialize()
        }

        startKoin {
            modules(
                appModule(this@MyApplication),
                searchModule,
                module { single { activityProvider } },
            )
        }
    }
}