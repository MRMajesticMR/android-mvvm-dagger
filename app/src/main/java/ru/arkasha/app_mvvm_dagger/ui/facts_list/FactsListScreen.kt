package ru.arkasha.app_mvvm_dagger.ui.facts_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import kotlinx.android.synthetic.main.f_facts_list.*
import ru.arkasha.app_mvvm_dagger.R
import ru.arkasha.app_mvvm_dagger.base.recycler_view.tuneVertical

class FactsListScreen : Fragment(R.layout.f_facts_list) {

    private val viewModel: FactsListScreenViewModel by viewModels()

    private val factsRecyclerViewAdapter = FactsRecyclerViewAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rvFacts.tuneVertical(factsRecyclerViewAdapter)

        viewModel.uiCatsFacts.observe(viewLifecycleOwner, { facts ->
            factsRecyclerViewAdapter.setData(facts)
        })

        viewModel.uiContentState.observe(viewLifecycleOwner, { state ->
            when (state) {
                ContentState.LOADING -> {
                    tvLoading?.visibility = View.VISIBLE
                    rvFacts?.visibility = View.GONE
                    tvError?.visibility = View.GONE
                }

                ContentState.CONTENT -> {
                    tvLoading?.visibility = View.GONE
                    rvFacts?.visibility = View.VISIBLE
                    tvError?.visibility = View.GONE
                }

                ContentState.ERROR -> {
                    tvLoading?.visibility = View.GONE
                    rvFacts?.visibility = View.GONE
                    tvError?.visibility = View.VISIBLE
                }
            }
        })
    }

}