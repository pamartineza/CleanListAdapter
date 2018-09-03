package com.greenlionsoft.cleanlist.lib

/**
 * Use with care, as checking complete equality may lead to inefficiency
 */
class DefaultCleanListAsyncDiffUtil<T> : CleanListAsyncDiffUtil<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return true
    }
}