package ru.arkasha.app_mvvm_dagger.ui.facts_list

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val factsListUiModule = module {

    viewModel {
        FactsListScreenViewModel(
            factsRepository = get()
        )
    }

}