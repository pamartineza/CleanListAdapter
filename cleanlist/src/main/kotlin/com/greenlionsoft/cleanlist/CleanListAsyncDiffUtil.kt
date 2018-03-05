package com.greenlionsoft.cleanlist

import android.support.v7.util.DiffUtil


abstract class CleanListAsyncDiffUtil<ItemClass> : DiffUtil.ItemCallback<ItemClass>() {

    abstract override fun areItemsTheSame(oldItem: ItemClass, newItem: ItemClass): Boolean

    abstract override fun areContentsTheSame(oldItem: ItemClass, newItem: ItemClass): Boolean

}