package ru.arkasha.cats_api

import retrofit2.http.GET
import ru.arkasha.cats_api.objects.CatFactCatsApiObject

interface CatsApi {

    /**
     * Get facts about cats
     */
    @GET("facts")
    suspend fun getFacts(): List<CatFactCatsApiObject>


}
