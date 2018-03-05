package com.greenlionsoft.cleanlistadapterdemo

import com.greenlionsoft.cleanlist.CleanListAsyncDiffUtil


class DemoAsyncListDiffUtil : CleanListAsyncDiffUtil<DemoItem>() {

    override fun areItemsTheSame(oldItem: DemoItem, newItem: DemoItem): Boolean {
        return oldItem.equals(newItem)
    }

    override fun areContentsTheSame(oldItem: DemoItem, newItem: DemoItem): Boolean {
        return true
    }
}