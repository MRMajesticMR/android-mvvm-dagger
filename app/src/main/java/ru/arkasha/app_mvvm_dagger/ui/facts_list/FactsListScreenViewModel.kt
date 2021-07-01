package ru.arkasha.app_mvvm_dagger.ui.facts_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.orhanobut.logger.Logger

class FactsListScreenViewModel : ViewModel() {

    private val model = DataModel(
        text = "Start text"
    )

    val uiTextLiveData = MutableLiveData<String>()

    init {
        Logger.d("FactsListScreenViewModel created!")

        getText()
    }

    fun getText() {
        uiTextLiveData.postValue(model.text)
    }

    override fun onCleared() {
        super.onCleared()

        Logger.d("FactsListScreenViewModel destroyed!")
    }

}