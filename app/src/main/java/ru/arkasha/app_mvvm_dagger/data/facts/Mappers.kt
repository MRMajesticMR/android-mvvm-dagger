package ru.arkasha.app_mvvm_dagger.data.facts

import ru.arkasha.cats_api.objects.CatFactCatsApiObject
import javax.inject.Inject

class CatFactFromCatFactCatsApiObjectMapper @Inject constructor() {

    fun map(id: String, from: CatFactCatsApiObject) =
        CatFact(
            id = id,
            title = from.text,
            creationDate = from.createdAt
        )

}