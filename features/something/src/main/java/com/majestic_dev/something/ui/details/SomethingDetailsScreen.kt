package com.majestic_dev.something.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.majestic_dev.something.R
import com.majestic_dev.something.databinding.SSomethingDetailsBinding
import ru.arkasha.view_binding_utils.base.ViewBindingSupportFragment

class SomethingDetailsScreen: ViewBindingSupportFragment<SSomethingDetailsBinding>(R.layout.s_something_details) {

    override fun bind(view: View): SSomethingDetailsBinding =
        SSomethingDetailsBinding.bind(view)

    override fun initView(view: View, savedInstanceState: Bundle?) {
        binding?.bButton?.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}