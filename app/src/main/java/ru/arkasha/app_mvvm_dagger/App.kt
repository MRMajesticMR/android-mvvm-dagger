package ru.arkasha.app_mvvm_dagger

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger


class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        Logger.addLogAdapter(AndroidLogAdapter())

        appComponent = DaggerAppComponent.create()
    }

}

val Fragment.appComponent: AppComponent
    get() = context?.appComponent
        ?: throw IllegalStateException("Try to get AppComponent when fragment detached: ${javaClass.canonicalName}")


val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> appComponent
        else -> this.applicationContext.appComponent
    }