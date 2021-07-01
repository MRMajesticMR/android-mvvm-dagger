package ru.arkasha.app_mvvm_dagger.ui.facts_list

import androidx.lifecycle.ViewModel
import com.orhanobut.logger.Logger

class FactsListScreenViewModel : ViewModel() {

    var word = ""

    var score = 0

    private lateinit var wordList: MutableList<String>

    init {
        Logger.d("FactsListScreenViewModel created!")

        resetList()
        nextWord()
    }

    /**
     * Moves to the next word in the list
     */
    private fun nextWord() {
        if (wordList.isNotEmpty()) {
            //Select and remove a word from the list
            word = wordList.removeAt(0)
        }
    }
    /** Methods for buttons presses **/
    fun onSkip() {
        score--
        nextWord()
    }

    fun onCorrect() {
        score++
        nextWord()
    }

    private fun resetList() {
        wordList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"
        )
        wordList.shuffle()
    }

    override fun onCleared() {
        super.onCleared()

        Logger.d("FactsListScreenViewModel destroyed!")
    }

}