package ru.arkasha.app_mvvm_dagger.base.recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class SingleViewHolderRecyclerViewAdapter<T>: BaseDataAdapter<T, SingleViewHolderRecyclerViewAdapter<T>.ViewHolder>() {

    abstract val viewHolderLayoutId: Int

    protected open fun initViewHolder(holder: ViewHolder) {}

    abstract fun bindModel(holder: ViewHolder, model: T)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent).apply { initViewHolder(this) }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        bindModel(holder, data[position])

    inner class ViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            viewHolderLayoutId,
            parent,
            false
        )
    )

}