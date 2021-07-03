package ru.arkasha.app_mvvm_dagger.ui.facts_list

import kotlinx.android.synthetic.main.i_cat_fact.view.*
import ru.arkasha.app_mvvm_dagger.R
import ru.arkasha.app_mvvm_dagger.base.recycler_view.SingleViewHolderRecyclerViewAdapter
import ru.arkasha.app_mvvm_dagger.data.facts.CatFact

class FactsRecyclerViewAdapter : SingleViewHolderRecyclerViewAdapter<CatFact>() {

    override val viewHolderLayoutId: Int = R.layout.i_cat_fact

    override fun bindModel(holder: ViewHolder, model: CatFact) {
        with(holder.itemView) {
            tvDate.text = model.creationDate
            tvText.text = model.title

            setOnClickListener {  }
        }
    }
}