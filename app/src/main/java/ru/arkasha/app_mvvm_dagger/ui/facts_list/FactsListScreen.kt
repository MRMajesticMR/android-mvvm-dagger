package ru.arkasha.app_mvvm_dagger.ui.facts_list

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.orhanobut.logger.Logger
import ru.arkasha.app_mvvm_dagger.R

class FactsListScreen : Fragment() {

    private lateinit var viewModel: FactsListScreenViewModel

    @SuppressLint("InflateParams")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.f_facts_list, null)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Logger.d("Called FactsListScreenViewModel.get")
        viewModel = ViewModelProvider(this).get(FactsListScreenViewModel::class.java)
    }

}