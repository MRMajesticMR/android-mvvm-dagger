package ru.arkasha.app_mvvm_dagger.ui.facts_list

import ru.arkasha.app_mvvm_dagger.data.facts.CatFact

data class DataModel(
    val text: String = "",
    val facts: List<CatFact> = emptyList(),
    val contentState: ContentState = ContentState.LOADING
) {

    enum class ContentState {

        LOADING,

        CONTENT,

        ERROR

    }

}