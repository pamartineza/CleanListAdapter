package com.greenlionsoft.cleanlistadapterdemo.composed

import com.greenlionsoft.cleanlist.mvp.CleanListPresenter
import com.greenlionsoft.cleanlist.mvp.ComposedCleanListPresenter
import com.greenlionsoft.cleanlist.mvp.IComposedCleanListPresenter
import com.greenlionsoft.cleanlistadapterdemo.DemoItem
import com.greenlionsoft.cleanlistadapterdemo.DemoItemHolder
import java.util.*


class ComposedPresenter(val view : IComposedPresenterView ) : IComposedCleanListPresenter by ComposedCleanListPresenter(view), DemoItemHolder.IDemoItemHolderExtraCallbacks {

    val itemList: MutableList<DemoItem> = mutableListOf()

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

    interface IComposedPresenterView : CleanListPresenter.ICleanListView<DemoItem> {

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