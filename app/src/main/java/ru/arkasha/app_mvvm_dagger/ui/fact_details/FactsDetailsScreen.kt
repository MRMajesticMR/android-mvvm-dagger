package ru.arkasha.app_mvvm_dagger.ui.fact_details

import android.os.Bundle
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import dagger.Lazy
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.arkasha.app_mvvm_dagger.R
import ru.arkasha.app_mvvm_dagger.appComponent
import ru.arkasha.app_mvvm_dagger.databinding.FFactDetailsBinding
import ru.arkasha.app_mvvm_dagger.utils.recycler_view.decorators.MarginBottomListDecoration
import ru.arkasha.app_mvvm_dagger.utils.recycler_view.decorators.MarginTopListDecoration
import ru.arkasha.view_binding_utils.base.ViewBindingSupportFragment
import javax.inject.Inject

class FactsDetailsScreen :
    ViewBindingSupportFragment<FFactDetailsBinding>(R.layout.f_fact_details) {

    private val args: FactsDetailsScreenArgs by navArgs()

    @Inject
    internal lateinit var viewModelFactory: Lazy<ViewModel.Factory>

    private val viewModel: ViewModel by viewModels {
        viewModelFactory.get().apply {
            setFactId(args.factId)
        }
    }

    override fun bind(view: View): FFactDetailsBinding =
        FFactDetailsBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        appComponent.inject(this)

        ViewCompat.setOnApplyWindowInsetsListener(view) { _, windowInsets ->
            val systemBarsInsets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())

            view.updatePadding(
                top = systemBarsInsets.top,
                bottom = systemBarsInsets.bottom
            )

            WindowInsetsCompat.CONSUMED
        }

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

        binding?.bActionButton?.setOnClickListener {
            binding?.bActionButton?.isEnabled = false
            binding?.bActionButtonOutlined?.isEnabled = true
        }

        binding?.bActionButtonOutlined?.setOnClickListener {
            binding?.bActionButton?.isEnabled = true
            binding?.bActionButtonOutlined?.isEnabled = false
        }
    }

}