package com.plcoding.room_cmp

import App
import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import appModuleAn
import com.plcoding.room_cmp.database.getUserDataBase
import di.appModule
import di.repositoryModule
import di.useCasesModule
import di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dao = getUserDataBase(applicationContext).userDao()
        setContent {
            App(dao)
        }
    }
}

class AppInject : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppInject)
            androidLogger()
            modules(appModule, repositoryModule, useCasesModule, viewModelModule, appModuleAn)
        }
    }
}