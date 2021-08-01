package ru.arkasha.app_mvvm_dagger.data

import dagger.Module
import dagger.Provides
import ru.arkasha.app_mvvm_dagger.api.cats.CatsApi
import ru.arkasha.app_mvvm_dagger.data.facts.CatFactFromCatFactCatsApiObjectMapper
import ru.arkasha.app_mvvm_dagger.data.facts.FactsRepository
import ru.arkasha.app_mvvm_dagger.data.facts.FactsRepositoryImpl

@Module
object DataModule {

    @Provides
    fun provideFactsRepository(
        catsApi: CatsApi
    ): FactsRepository =
        FactsRepositoryImpl(
            catsApi = catsApi,
            catFactFromCatFactCatsApiObjectMapper = CatFactFromCatFactCatsApiObjectMapper()
        )

}