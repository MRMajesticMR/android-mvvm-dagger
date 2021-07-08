package ru.arkasha.app_mvvm_dagger.ui.fact_details

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import ru.arkasha.app_mvvm_dagger.R
import ru.arkasha.app_mvvm_dagger.base.fragments.ViewBindingSupportFragment
import ru.arkasha.app_mvvm_dagger.databinding.FFactDetailsBinding

class FactsDetailsScreen : ViewBindingSupportFragment<FFactDetailsBinding>(R.layout.f_fact_details) {

    private val args: FactsDetailsScreenArgs by navArgs()

    private val viewModel: FactDetailsScreenViewModel by viewModel { parametersOf(args.factId) }

    override fun bind(view: View): FFactDetailsBinding =
        FFactDetailsBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.uiFact.observe(viewLifecycleOwner, { fact ->
            binding?.tvDate?.text = fact.creationDate
            binding?.tvText?.text = fact.title
        })
    }

}