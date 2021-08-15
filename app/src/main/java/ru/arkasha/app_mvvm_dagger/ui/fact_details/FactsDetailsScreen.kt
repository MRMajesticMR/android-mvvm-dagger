package ru.arkasha.app_mvvm_dagger.ui.fact_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.arkasha.app_mvvm_dagger.R
import ru.arkasha.app_mvvm_dagger.databinding.FFactDetailsBinding
import ru.arkasha.view_binding_utils.base.ViewBindingSupportFragment

class FactsDetailsScreen :
    ViewBindingSupportFragment<FFactDetailsBinding>(R.layout.f_fact_details) {

    private val args: FactsDetailsScreenArgs by navArgs()

    private val viewModel: ViewModel by viewModels()

    override fun bind(view: View): FFactDetailsBinding =
        FFactDetailsBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedElementEnterTransition = TransitionInflater
            .from(context)
            .inflateTransition(android.R.transition.move)

        postponeEnterTransition()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.stateFact.collect { fact ->
                    if (fact != null) {
                        binding?.tvDate?.text = fact.creationDate
                        binding?.tvText?.text = fact.title
                    }

                    startPostponedEnterTransition()
                }
            }
        }
    }

}