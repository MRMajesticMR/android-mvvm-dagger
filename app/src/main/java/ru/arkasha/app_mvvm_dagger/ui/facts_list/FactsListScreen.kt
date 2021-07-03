package ru.arkasha.app_mvvm_dagger.ui.facts_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import ru.arkasha.app_mvvm_dagger.R
import ru.arkasha.app_mvvm_dagger.base.fragments.ViewBindingSupportFragment
import ru.arkasha.app_mvvm_dagger.base.recycler_view.tuneVertical
import ru.arkasha.app_mvvm_dagger.databinding.FFactsListBinding

class FactsListScreen : ViewBindingSupportFragment<FFactsListBinding>(R.layout.f_facts_list) {

    private val viewModel: FactsListScreenViewModel by viewModels()

    private val factsRecyclerViewAdapter = FactsRecyclerViewAdapter()

    override fun bind(view: View): FFactsListBinding =
        FFactsListBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.rvFacts?.tuneVertical(factsRecyclerViewAdapter)

        viewModel.uiCatsFacts.observe(viewLifecycleOwner, { facts ->
            factsRecyclerViewAdapter.setData(facts)
        })

        viewModel.uiContentState.observe(viewLifecycleOwner, { state ->
            when (state) {
                ContentState.LOADING -> {
                    binding?.tvLoading?.visibility = View.VISIBLE
                    binding?.rvFacts?.visibility = View.GONE
                    binding?.tvError?.visibility = View.GONE
                }

                ContentState.CONTENT -> {
                    binding?.tvLoading?.visibility = View.GONE
                    binding?.rvFacts?.visibility = View.VISIBLE
                    binding?.tvError?.visibility = View.GONE
                }

                ContentState.ERROR -> {
                    binding?.tvLoading?.visibility = View.GONE
                    binding?.rvFacts?.visibility = View.GONE
                    binding?.tvError?.visibility = View.VISIBLE
                }

                else -> throw IllegalStateException("Illegal state: $state")
            }
        })
    }

}