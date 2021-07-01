package ru.arkasha.app_mvvm_dagger.data.facts

import ru.arkasha.app_mvvm_dagger.api.cats.CatsApi

interface FactsRepository {

    /**
     * Get facts
     */
    suspend fun getFacts(): List<CatFact>

}

class FactsRepositoryImpl(
    private val catsApi: CatsApi,
    private val catFactFromCatFactCatsApiObjectMapper: CatFactFromCatFactCatsApiObjectMapper
) : FactsRepository {

    override suspend fun getFacts(): List<CatFact> =
        catsApi.getFacts().map { catFactFromCatFactCatsApiObjectMapper.map(it) }

}