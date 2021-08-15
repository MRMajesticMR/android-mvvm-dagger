package ru.arkasha.app_mvvm_dagger.data

import dagger.Module
import dagger.Provides
import ru.arkasha.app_mvvm_dagger.data.facts.CatFactFromCatFactCatsApiObjectMapper
import ru.arkasha.app_mvvm_dagger.data.facts.FactsRepository
import ru.arkasha.app_mvvm_dagger.data.facts.FactsRepositoryImpl
import ru.arkasha.cats_api.CatsApi
import javax.inject.Singleton

@Module
object DataModule {

    @Provides
    @Singleton
    fun provideFactsRepository(
        catsApi: CatsApi,
        catFactFromCatFactCatsApiObjectMapper: CatFactFromCatFactCatsApiObjectMapper
    ): FactsRepository =
        FactsRepositoryImpl(
            catsApi = catsApi,
            catFactFromCatFactCatsApiObjectMapper = catFactFromCatFactCatsApiObjectMapper
        )


}