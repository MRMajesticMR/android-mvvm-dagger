package ru.arkasha.app_mvvm_dagger.data.facts

import ru.arkasha.app_mvvm_dagger.api.cats.objects.CatFactCatsApiObject

class CatFactFromCatFactCatsApiObjectMapper {

    fun map(id: String, from: CatFactCatsApiObject) =
        CatFact(
            id = id,
            title = from.text,
            creationDate = from.createdAt
        )

}