package com.greenlionsoft.cleanlistadapterdemo

import com.greenlionsoft.cleanlist.mvp.CleanListPresenter
import java.util.*


class DemoPresenter(val view: IDemoView): CleanListPresenter<DemoItem>(view), DemoItemHolder.IDemoItemHolderExtraCallbacks {

    val itemList: MutableList<DemoItem> = mutableListOf()

    /**
     * Example of use:
     *
     * 1 - Show progress
     * 2 - Get data from repository (any async solution should be used)
     * 3 - Update Clean list
     * 4 - Hide progress
     *
     */
    fun onViewReady() {

        view.showProgress()
        itemList.addAll(fakeItemRepository())
        view.updateCleanList(itemList)
        view.hideProgress()
    }

    //clean list callbacks

    override fun onItemPressed(position: Int) {
        view.showItemPressedMesage(position)
    }

    override fun onItemLongPressed(position: Int) {
        view.showItemLongPressedMessage(position)
    }

    override fun onLeftSwipe(adapterPosition: Int) {
        itemList.removeAt(adapterPosition)
        view.updateCleanList(itemList)
    }

    override fun onItemDrag(oldPosition: Int, newPosition: Int) {
        Collections.swap(itemList, oldPosition, newPosition)
        view.updateCleanList(itemList)
    }

    //example of extra callbacks

    override fun onNamePressed(position: Int) {
        view.showNamePressedMessage(position)
    }

    override fun onAgePressed(position: Int) {
        view.showAgePressedMessage(position)
    }

    interface IDemoView : ICleanListView<DemoItem> {

        fun showProgress()

        fun hideProgress()

        fun showItemPressedMesage(position: Int)

        fun showItemLongPressedMessage(position: Int)

        fun showNamePressedMessage(position: Int)

        fun showAgePressedMessage(position: Int)

    }

    private fun fakeItemRepository(): List<DemoItem> {
        return listOf<DemoItem>(
                DemoItem("Pepe López", 32),
                DemoItem("María Flórez Gil", 23),
                DemoItem("Carlota Gutierrez Martínez", 12),
                DemoItem("Pablo A. Iturbe", 26),
                DemoItem("Aída Farrer", 45))
    }


}