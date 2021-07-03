package ru.arkasha.app_mvvm_dagger.ui.facts_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.arkasha.app_mvvm_dagger.R
import ru.arkasha.app_mvvm_dagger.base.recycler_view.tuneVertical
import ru.arkasha.app_mvvm_dagger.databinding.FFactsListBinding

class FactsListScreen : Fragment(R.layout.f_facts_list) {

    private val viewModel: FactsListScreenViewModel by viewModels()

    private var binding: FFactsListBinding? = null

    private val factsRecyclerViewAdapter = FactsRecyclerViewAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FFactsListBinding.bind(view)

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

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }

}