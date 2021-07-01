package ru.arkasha.app_mvvm_dagger.api.cats

import io.reactivex.Single
import retrofit2.http.GET
import ru.arkasha.app_mvvm_dagger.api.cats.objects.CatFactCatsApiObject

interface CatsApi {

    /**
     * Get facts about cats
     */
    @GET("facts")
    fun getFacts(): Single<List<CatFactCatsApiObject>>


}
