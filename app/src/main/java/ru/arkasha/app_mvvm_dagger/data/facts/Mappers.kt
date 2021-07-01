package ru.arkasha.app_mvvm_dagger.data.facts

import ru.arkasha.app_mvvm_dagger.api.cats.objects.CatFactCatsApiObject

class CatFactFromCatFactCatsApiObjectMapper {

    fun map(from: CatFactCatsApiObject) =
        CatFact(
            title = from.text,
            creationDate = from.createdAt
        )

}