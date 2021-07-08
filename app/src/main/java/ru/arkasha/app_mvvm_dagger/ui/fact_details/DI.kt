package ru.arkasha.app_mvvm_dagger.ui.fact_details

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val factsDetailsUiModule = module {

    viewModel {
        FactDetailsScreenViewModel(
            factId = get(),
            factsRepository = get()
        )
    }

}