package ru.arkasha.app_mvvm_dagger.ui.facts_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import dagger.Lazy
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.arkasha.app_mvvm_dagger.R
import ru.arkasha.app_mvvm_dagger.appComponent
import ru.arkasha.app_mvvm_dagger.base.recycler_view.tuneVertical
import ru.arkasha.app_mvvm_dagger.databinding.FFactsListBinding
import ru.arkasha.view_binding_utils.base.ViewBindingSupportFragment
import javax.inject.Inject

class FactsListScreen : ViewBindingSupportFragment<FFactsListBinding>(R.layout.f_facts_list) {

    @Inject
    internal lateinit var viewModelFactory: Lazy<ViewModel.Factory>

    private val viewModel: ViewModel by viewModels { viewModelFactory.get() }

    private val factsRecyclerViewAdapter by lazy {
        FactsRecyclerViewAdapter().apply {
            onClicked = { view, model ->
                findNavController().navigate(
                    FactsListScreenDirections.actionFactsListScreenToFactsDetailsScreen(model.id),
                    FragmentNavigatorExtras(view to "rootView")
                )
            }
        }
    }

    override fun bind(view: View): FFactsListBinding =
        FFactsListBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        appComponent.inject(this)

        binding?.rvFacts?.tuneVertical(factsRecyclerViewAdapter)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.stateCatsFacts.collect { facts ->
                    factsRecyclerViewAdapter.setData(facts)

                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.stateContentState.collect { state ->
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
                    }
                }
            }
        }
    }
}