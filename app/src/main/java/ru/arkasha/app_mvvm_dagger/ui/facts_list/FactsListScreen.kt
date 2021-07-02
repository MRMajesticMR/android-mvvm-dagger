package ru.arkasha.app_mvvm_dagger.ui.facts_list

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.arkasha.app_mvvm_dagger.R

class FactsListScreen : Fragment(R.layout.f_facts_list) {

    private val viewModel: FactsListScreenViewModel by viewModels()

    private val tvText by lazy { view?.findViewById<TextView>(R.id.tvText) }
    private val clContent by lazy { view?.findViewById<View>(R.id.clContent) }
    private val tvError by lazy { view?.findViewById<TextView>(R.id.tvError) }
    private val tvLoading by lazy { view?.findViewById<TextView>(R.id.tvLoading) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.uiTextLiveData.observe(viewLifecycleOwner, { updatedText ->
            tvText?.text = updatedText
        })

        viewModel.uiCatsFacts.observe(viewLifecycleOwner, { facts ->
            tvText?.text = facts.joinToString("\n\n") {
                "- ${it.title}"
            }
        })

        viewModel.uiContentState.observe(viewLifecycleOwner, { state ->
            when(state) {
                DataModel.ContentState.LOADING -> {
                    tvLoading?.visibility = View.VISIBLE
                    clContent?.visibility = View.GONE
                    tvError?.visibility = View.GONE
                }

                DataModel.ContentState.CONTENT -> {
                    tvLoading?.visibility = View.GONE
                    clContent?.visibility = View.VISIBLE
                    tvError?.visibility = View.GONE
                }

                DataModel.ContentState.ERROR -> {
                    tvLoading?.visibility = View.GONE
                    clContent?.visibility = View.GONE
                    tvError?.visibility = View.VISIBLE
                }
            }
        })
    }

}