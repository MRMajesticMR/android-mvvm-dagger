package ru.arkasha.app_mvvm_dagger.ui.facts_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.arkasha.app_mvvm_dagger.data.facts.CatFact
import ru.arkasha.app_mvvm_dagger.data.facts.FactsRepository
import javax.inject.Inject

class ViewModel(
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

    class Factory @Inject constructor(
        private val factsRepository: FactsRepository
    ): ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            ViewModel(
                factsRepository = factsRepository
            ) as T

    }

}