package ru.arkasha.app_mvvm_dagger.ui.fact_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.arkasha.app_mvvm_dagger.data.facts.CatFact
import ru.arkasha.app_mvvm_dagger.data.facts.FactsRepository

class ViewModel(
    private val factId: String,
    private val factsRepository: FactsRepository
) : ViewModel() {

    val uiFact = MutableLiveData<CatFact>()

    init {
        loadFact()
    }

    private fun loadFact() {
        viewModelScope.launch {
            factsRepository.findFactInCache(factId)?.let {
                uiFact.value = it
            }
        }
    }

}