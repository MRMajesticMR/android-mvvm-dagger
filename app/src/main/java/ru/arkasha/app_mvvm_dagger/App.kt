package ru.arkasha.app_mvvm_dagger

import android.app.Application
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.arkasha.app_mvvm_dagger.api.apiModule
import ru.arkasha.app_mvvm_dagger.data.dataModule
import ru.arkasha.app_mvvm_dagger.ui.fact_details.factsDetailsUiModule
import ru.arkasha.app_mvvm_dagger.ui.facts_list.factsListUiModule


class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Logger.addLogAdapter(AndroidLogAdapter())

        initKoin()
    }

    private fun initKoin() {
        startKoin {

            androidContext(this@App)

            modules(
                listOf(
                    apiModule,
                    dataModule,

                    factsListUiModule,
                    factsDetailsUiModule
                )
            )

        }
    }

}