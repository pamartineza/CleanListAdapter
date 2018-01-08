package com.greenlionsoft.cleanlist

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import com.greenlionsoft.cleanlist.mvp.CleanListPresenter


class CleanListTouchCallback<T>(val cleanListPresenter: CleanListPresenter<T>,
                                val isDragEnabled: Boolean,
                                val isLeftSwipeEnabled: Boolean,
                                val isRightSwipeEnabled: Boolean) : ItemTouchHelper.Callback() {


    override fun getMovementFlags(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?): Int {

        var dragFlags = 0
        var swipeFlags = 0

        if (isDragEnabled) {
            dragFlags = ItemTouchHelper.DOWN or ItemTouchHelper.UP or ItemTouchHelper.START or ItemTouchHelper.END
        }

        if (isLeftSwipeEnabled) {
            swipeFlags = ItemTouchHelper.START
        }

        if (isRightSwipeEnabled) {
            swipeFlags = swipeFlags or ItemTouchHelper.END
        }

        return ItemTouchHelper.Callback.makeMovementFlags(dragFlags, swipeFlags)

    }

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        cleanListPresenter.onItemDrag(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        if (direction == ItemTouchHelper.START) {
            cleanListPresenter.onLeftSwipe(viewHolder.adapterPosition)

        } else if (direction == ItemTouchHelper.END) {
            cleanListPresenter.onRightSwipe(viewHolder.adapterPosition)
        }
    }

    override fun isLongPressDragEnabled(): Boolean {
        return false
    }
}