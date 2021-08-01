package ru.arkasha.app_mvvm_dagger

import dagger.Component
import ru.arkasha.app_mvvm_dagger.api.ApiModule
import ru.arkasha.app_mvvm_dagger.data.DataModule
import ru.arkasha.app_mvvm_dagger.data.facts.FactsRepository
import ru.arkasha.app_mvvm_dagger.ui.facts_list.FactsListScreenViewModel

@Component(
    modules = [
        ApiModule::class,
        DataModule::class,
    ]
)
interface AppComponent {

    fun inject(factsListScreenViewModel: FactsListScreenViewModel)

    val factsRepository: FactsRepository

}