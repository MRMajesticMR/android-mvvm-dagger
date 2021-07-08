package ru.arkasha.app_mvvm_dagger.ui.facts_list

import android.view.View
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import ru.arkasha.app_mvvm_dagger.R
import ru.arkasha.app_mvvm_dagger.base.recycler_view.SingleViewHolderRecyclerViewAdapter
import ru.arkasha.app_mvvm_dagger.data.facts.CatFact
import ru.arkasha.app_mvvm_dagger.databinding.ICatFactBinding

class FactsRecyclerViewAdapter : SingleViewHolderRecyclerViewAdapter<CatFact>() {

    private var binding: ICatFactBinding? = null

    override val viewHolderLayoutId: Int = R.layout.i_cat_fact

    var onClicked: (view: View, model: CatFact) -> Unit = { _, _ -> }

    override fun bindModel(holder: ViewHolder, model: CatFact) {
        with(holder.itemView) {
            binding = ICatFactBinding.bind(this)

            ViewCompat.setTransitionName(this, model.id)

            binding?.tvDate?.text = model.creationDate
            binding?.tvText?.text = model.title

            setOnClickListener { onClicked(this, model) }
        }
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)

        binding = null
    }

}