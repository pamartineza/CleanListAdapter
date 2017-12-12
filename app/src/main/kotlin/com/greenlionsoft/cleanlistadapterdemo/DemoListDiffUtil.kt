package com.greenlionsoft.cleanlistadapterdemo

import com.greenlionsoft.cleanlist.CleanListDiffUtil


class DemoListDiffUtil : CleanListDiffUtil<DemoItem>() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return oldItem.equals(newItem)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return true
    }
}