package ru.arkasha.app_mvvm_dagger

import dagger.Binds
import dagger.Component
import dagger.Module
import ru.arkasha.app_mvvm_dagger.api.ApiModule
import ru.arkasha.app_mvvm_dagger.data.facts.FactsRepository
import ru.arkasha.app_mvvm_dagger.data.facts.FactsRepositoryImpl
import ru.arkasha.app_mvvm_dagger.ui.facts_list.FactsListScreen

@Component(
    modules = [
        ApiModule::class,
        AppBindModule::class
    ]
)
interface AppComponent {

    fun inject(to: FactsListScreen)

}

@Module
interface AppBindModule {

    @Suppress("FunctionName")
    @Binds
    fun bind_FactsRepositoryImpl_to_FactsRepository(
        factsRepositoryImpl: FactsRepositoryImpl
    ): FactsRepository

}