package com.greenlionsoft.cleanlist

import android.support.v7.util.DiffUtil


abstract class CleanListDiffUtil<ItemClass> : DiffUtil.Callback() {

    protected var oldList: List<ItemClass> = emptyList()
    protected var newList: List<ItemClass> = emptyList()

    fun setLists(oldList: List<ItemClass>, newList: List<ItemClass>) {
        this.oldList = oldList
        this.newList = newList
    }

    abstract override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    abstract override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean
}