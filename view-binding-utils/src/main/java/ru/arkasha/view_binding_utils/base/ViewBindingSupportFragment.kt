package ru.arkasha.view_binding_utils.base

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

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