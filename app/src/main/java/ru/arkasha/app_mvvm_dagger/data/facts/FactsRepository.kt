package ru.arkasha.app_mvvm_dagger.data.facts

import ru.arkasha.cats_api.CatsApi
import javax.inject.Inject

interface FactsRepository {

    /**
     * Get facts
     */
    suspend fun getFacts(): List<CatFact>

    /**
     * Searching for fact in cache. Return null if no value found
     */
    suspend fun findFactInCache(id: String): CatFact?

}

class FactsRepositoryImpl @Inject constructor(
    private val catsApi: CatsApi,
    private val catFactFromCatFactCatsApiObjectMapper: CatFactFromCatFactCatsApiObjectMapper
) : FactsRepository {

    private val mCache = mutableListOf<CatFact>()

    override suspend fun getFacts(): List<CatFact> =
        if (mCache.isEmpty()) {
            val results = catsApi.getFacts().mapIndexed { index, model ->
                catFactFromCatFactCatsApiObjectMapper.map(index.toString(), model)
            }

            mCache.clear()
            mCache.addAll(results)

            results
        } else {
            mCache.toList()
        }

    override suspend fun findFactInCache(id: String): CatFact? =
        mCache.firstOrNull { it.id == id }

}