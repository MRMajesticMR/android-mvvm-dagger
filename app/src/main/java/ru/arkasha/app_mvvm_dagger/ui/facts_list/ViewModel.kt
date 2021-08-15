package ru.arkasha.app_mvvm_dagger.ui.facts_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.arkasha.app_mvvm_dagger.data.facts.CatFact
import ru.arkasha.app_mvvm_dagger.data.facts.FactsRepository
import javax.inject.Inject

class ViewModel(
    private val factsRepository: FactsRepository
) : ViewModel() {

    private val _stateCatsFacts = MutableStateFlow<List<CatFact>>(emptyList())
    val stateCatsFacts: StateFlow<List<CatFact>> = _stateCatsFacts

    private val _stateContentState = MutableStateFlow(ContentState.LOADING)
    val stateContentState: StateFlow<ContentState> = _stateContentState

    init {
        loadCatFacts()
    }

    private fun loadCatFacts() {
        viewModelScope.launch {
            _stateContentState.value = ContentState.LOADING

            try {
                _stateCatsFacts.value = factsRepository.getFacts()

                _stateContentState.value = ContentState.CONTENT
            } catch (e: Throwable) {
                _stateContentState.value = ContentState.ERROR
            }
        }
    }

    class Factory @Inject constructor(
        private val factsRepository: FactsRepository
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            ViewModel(
                factsRepository = factsRepository
            ) as T

    }

}