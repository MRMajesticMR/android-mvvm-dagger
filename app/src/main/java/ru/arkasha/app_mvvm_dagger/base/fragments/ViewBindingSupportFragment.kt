package ru.arkasha.app_mvvm_dagger.base.fragments

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import ru.arkasha.app_mvvm_dagger.ui.facts_list.FactsListScreenViewModel

abstract class ViewBindingSupportFragment<B: ViewBinding>(@LayoutRes layoutId: Int): Fragment(layoutId) {

    protected var binding: B? = null

    protected abstract fun bind(view: View): B

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }

}