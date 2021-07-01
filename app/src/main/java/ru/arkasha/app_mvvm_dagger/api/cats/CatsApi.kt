package ru.arkasha.app_mvvm_dagger.api.cats

import retrofit2.http.GET
import ru.arkasha.app_mvvm_dagger.api.cats.objects.CatFactCatsApiObject

interface CatsApi {

    /**
     * Get facts about cats
     */
    @GET("facts")
    suspend fun getFacts(): List<CatFactCatsApiObject>


}
