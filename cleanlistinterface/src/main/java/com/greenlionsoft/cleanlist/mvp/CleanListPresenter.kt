package com.greenlionsoft.cleanlist.mvp


open class CleanListPresenter<T>(val cleanListView: ICleanListView<T>) : ICleanListItemHolderExtraCallbacks {


    open fun onItemPressed(position: Int) {
        //To be overridden
    }

    open fun onItemLongPressed(position: Int) {
        //To be overridden
    }

    open fun onItemDrag(oldPosition: Int, newPosition: Int) {
        //To be overridden
    }

    open fun onLeftSwipe(adapterPosition: Int) {
        //To be overridden
    }

    open fun onRightSwipe(adapterPosition: Int) {
        //To be overridden
    }

    interface ICleanListView<T> {

        fun updateCleanList(items: List<T>)

    }
}