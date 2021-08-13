package ru.arkasha.app_mvvm_dagger

import dagger.Component
import ru.arkasha.app_mvvm_dagger.data.DataModule
import ru.arkasha.app_mvvm_dagger.ui.facts_list.FactsListScreen

@Component(
    modules = [
        AppModule::class,
        DataModule::class
    ]
)
interface AppComponent {

    fun inject(to: FactsListScreen)

}