package ru.arkasha.app_mvvm_dagger.ui.facts_list

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import ru.arkasha.app_mvvm_dagger.R

class FactsListScreen : Fragment(R.layout.f_facts_list) {

    private val viewModel: FactsListScreenViewModel by activityViewModels()

    private val tvText by lazy { view?.findViewById<TextView>(R.id.tvText) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.uiTextLiveData.observe(viewLifecycleOwner, { updatedText ->
            tvText?.text = updatedText
        })
    }

}