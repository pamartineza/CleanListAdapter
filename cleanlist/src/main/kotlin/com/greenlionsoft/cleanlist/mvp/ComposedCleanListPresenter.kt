package com.greenlionsoft.cleanlist.mvp


class ComposedCleanListPresenter<T>(val view: CleanListPresenter.ICleanListView<T>) : IComposedCleanListPresenter {


    override fun onItemPressed(position: Int) {
        //To be overridden
    }

    override  fun onItemLongPressed(position: Int) {
        //To be overridden
    }

    override fun onItemDrag(oldPosition: Int, newPosition: Int) {
        //To be overridden
    }

    override fun onLeftSwipe(adapterPosition: Int) {
        //To be overridden
    }

    override fun onRightSwipe(adapterPosition: Int) {
        //To be overridden
    }

}

interface IComposedCleanListPresenter: ICleanListItemHolderExtraCallbacks {

    fun onItemPressed(position: Int)

    fun onItemLongPressed(position: Int)

    fun onItemDrag(oldPosition: Int, newPosition: Int)

    fun onLeftSwipe(adapterPosition: Int)

    fun onRightSwipe(adapterPosition: Int)
}