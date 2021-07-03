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

    private val mCache = mutableListOf<CatFact>()

    override suspend fun getFacts(): List<CatFact> =
        if (mCache.isEmpty()) {
            val results = catsApi.getFacts().map { catFactFromCatFactCatsApiObjectMapper.map(it) }

            mCache.clear()
            mCache.addAll(results)

            results
        } else {
            mCache.toList()
        }

}