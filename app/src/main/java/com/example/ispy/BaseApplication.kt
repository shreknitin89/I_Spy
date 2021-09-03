package com.example.ispy

import android.app.Application
import com.example.ispy.di.mainModule
import com.example.ispy.di.repoModule
import com.example.ispy.di.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BaseApplication)
            modules(
                mainModule + repoModule + useCaseModule
            )
        }
    }
}
