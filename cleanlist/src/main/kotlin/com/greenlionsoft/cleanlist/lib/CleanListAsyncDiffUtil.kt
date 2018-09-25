package com.greenlionsoft.cleanlist.lib

import androidx.recyclerview.widget.DiffUtil


abstract class CleanListAsyncDiffUtil<ItemClass> : DiffUtil.ItemCallback<ItemClass>() {

    abstract override fun areItemsTheSame(oldItem: ItemClass, newItem: ItemClass): Boolean

    abstract override fun areContentsTheSame(oldItem: ItemClass, newItem: ItemClass): Boolean

}