package ru.arkasha.app_mvvm_dagger.base.recycler_view

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.tuneVertical(adapter: RecyclerView.Adapter<*>, inverse: Boolean = false) {
    this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, inverse)
    this.adapter = adapter
}

fun RecyclerView.tuneHorizontal(adapter: RecyclerView.Adapter<*>, inverse: Boolean = false) {
    this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, inverse)
    this.adapter = adapter
}

fun RecyclerView.clearAllItemDecorations() {
    while (itemDecorationCount > 0) {
        removeItemDecorationAt(0)
    }
}