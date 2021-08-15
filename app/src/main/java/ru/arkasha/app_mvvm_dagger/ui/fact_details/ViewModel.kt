package ru.arkasha.app_mvvm_dagger.ui.fact_details

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
    private val factId: String,
    private val factsRepository: FactsRepository
) : ViewModel() {

    private val _stateFact = MutableStateFlow<CatFact?>(null)
    val stateFact: StateFlow<CatFact?> = _stateFact

    init {
        loadFact()
    }

    private fun loadFact() {
        viewModelScope.launch {
            factsRepository.findFactInCache(factId)?.let {
                _stateFact.value = it
            }
        }
    }

    class Factory @Inject constructor(
        private val factsRepository: FactsRepository
    ) : ViewModelProvider.Factory {

        private lateinit var factId: String

        fun setFactId(factId: String) {
            this.factId = factId
        }

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            ViewModel(
                factId = factId,
                factsRepository = factsRepository
            ) as T

    }

}