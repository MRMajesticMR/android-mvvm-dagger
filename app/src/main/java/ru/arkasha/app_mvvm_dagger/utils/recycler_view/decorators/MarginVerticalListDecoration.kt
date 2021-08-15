package ru.arkasha.app_mvvm_dagger.utils.recycler_view.decorators

import android.graphics.Rect
import android.view.View
import androidx.annotation.Px
import androidx.recyclerview.widget.RecyclerView

class MarginVerticalListDecoration(
    @Px private val margin: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val adapter = parent.adapter ?: return

        val position = parent.getChildAdapterPosition(view)

        if (position == adapter.itemCount - 1)
            outRect.bottom = this.margin

        if (position == 0)
            outRect.top = this.margin
    }
}