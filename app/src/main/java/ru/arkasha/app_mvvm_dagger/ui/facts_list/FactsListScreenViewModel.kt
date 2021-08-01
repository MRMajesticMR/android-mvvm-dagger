package ru.arkasha.app_mvvm_dagger.ui.facts_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.arkasha.app_mvvm_dagger.data.facts.CatFact
import ru.arkasha.app_mvvm_dagger.data.facts.FactsRepository
import javax.inject.Inject

class FactsListScreenViewModel @Inject constructor(
    private val factsRepository: FactsRepository
) : ViewModel() {

    val uiCatsFacts = MutableLiveData<List<CatFact>>()
    val uiContentState = MutableLiveData<ContentState>()

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