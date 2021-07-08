package ru.arkasha.app_mvvm_dagger.ui.facts_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.arkasha.app_mvvm_dagger.api.ApisProviderImpl
import ru.arkasha.app_mvvm_dagger.data.facts.CatFact
import ru.arkasha.app_mvvm_dagger.data.facts.CatFactFromCatFactCatsApiObjectMapper
import ru.arkasha.app_mvvm_dagger.data.facts.FactsRepository
import ru.arkasha.app_mvvm_dagger.data.facts.FactsRepositoryImpl

class FactsListScreenViewModel : ViewModel() {

    val uiCatsFacts = MutableLiveData<List<CatFact>>()
    val uiContentState = MutableLiveData<ContentState>()

    private val factsRepository: FactsRepository = FactsRepositoryImpl(
        catsApi = ApisProviderImpl().catsApi,
        catFactFromCatFactCatsApiObjectMapper = CatFactFromCatFactCatsApiObjectMapper()
    )

    init {
        loadCatFacts()
    }

    fun loadCatFacts() {
        viewModelScope.launch {
            uiContentState.value = ContentState.LOADING

            try {
                uiCatsFacts.value = factsRepository.getFacts()

                uiContentState.value = ContentState.CONTENT
            } catch (e: Throwable) {
                uiContentState.value = ContentState.ERROR
            }
        }
    }

}