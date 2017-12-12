package com.greenlionsoft.cleanlist

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView

/**
 *
 * Class to be extended by custom Holder
 *
 * It has already implemented optional listeners for click, long click, and drag
 * on a view with id "dragHandleIv"
 *
 */
abstract class CleanListItemHolder<in ItemClass>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var extraCallbacks: ICleanListItemHolderExtraCallbacks? = null

    fun setCallBacks(onItemClick: ((Int) -> Unit)?,
                     onItemLongClick: ((Int) -> Unit)?,
                     onDragHandleLongClick: ((RecyclerView.ViewHolder) -> Unit)?,
                     extraCallbacks: ICleanListItemHolderExtraCallbacks?) {

        itemView.setOnClickListener {
            onItemClick?.invoke(adapterPosition)
        }

        itemView.setOnLongClickListener {
            onItemLongClick?.invoke(adapterPosition)
            true
        }

        val id = itemView.context.resources.getIdentifier("dragHandleIv", "id", itemView.context.packageName)
        val dragHandle = itemView.findViewById<ImageView>(id)
        dragHandle?.let {

            it.setOnLongClickListener {
                onDragHandleLongClick?.invoke(this)
                true
            }
        }

        this.extraCallbacks = extraCallbacks

    }

    interface ICleanListItemHolderExtraCallbacks


    abstract fun fillWithData(item: ItemClass)

}