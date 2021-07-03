package ru.arkasha.app_mvvm_dagger.ui.facts_list

import androidx.recyclerview.widget.RecyclerView
import ru.arkasha.app_mvvm_dagger.R
import ru.arkasha.app_mvvm_dagger.base.recycler_view.SingleViewHolderRecyclerViewAdapter
import ru.arkasha.app_mvvm_dagger.data.facts.CatFact
import ru.arkasha.app_mvvm_dagger.databinding.ICatFactBinding

class FactsRecyclerViewAdapter : SingleViewHolderRecyclerViewAdapter<CatFact>() {

    private var binding: ICatFactBinding? = null

    override val viewHolderLayoutId: Int = R.layout.i_cat_fact

    override fun bindModel(holder: ViewHolder, model: CatFact) {
        with(holder.itemView) {
            binding = ICatFactBinding.bind(this)

            binding?.tvDate?.text = model.creationDate
            binding?.tvText?.text = model.title

            setOnClickListener { }
        }
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)

        binding = null
    }

}