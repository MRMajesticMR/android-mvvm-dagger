package ru.arkasha.app_mvvm_dagger.data

import org.koin.dsl.module
import ru.arkasha.app_mvvm_dagger.data.facts.CatFactFromCatFactCatsApiObjectMapper
import ru.arkasha.app_mvvm_dagger.data.facts.FactsRepository
import ru.arkasha.app_mvvm_dagger.data.facts.FactsRepositoryImpl

val dataModule = module {

    factory { CatFactFromCatFactCatsApiObjectMapper() }

    single<FactsRepository> {
        FactsRepositoryImpl(
            catsApi = get(),
            catFactFromCatFactCatsApiObjectMapper = get()
        )
    }

}