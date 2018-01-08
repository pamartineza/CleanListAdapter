package com.greenlionsoft.cleanlist

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.greenlionsoft.cleanlist.mvp.CleanListPresenter
import com.greenlionsoft.cleanlist.mvp.ICleanListItemHolderExtraCallbacks
import java.lang.reflect.Constructor
import java.lang.reflect.Method

/**
 * Powerful and simple Clean RecyclerView Adapter
 *
 * Steps to use:
 * 0- Define "ItemClass" to be displayed in Recyclerview
 * 1- Create a layout resource for the list items
 * 2- Create a Holder "HolderClass" that extends CleanListItemHolder2<ItemClass>
 * 3- Create CleanListAdapter2 and provide all desired callbacks and itemTouchHelper
 *
 */
class CleanListAdapter<ItemClass, HolderClass : CleanListItemHolder<ItemClass>>(
        val listItemLayoutResourceId: Int,
        val entityClass: Class<HolderClass>,
        val cleanListPresenter: CleanListPresenter<ItemClass>,
        var cleanListTouchCallback: CleanListTouchCallback<ItemClass>? = null,
        var diffUtil: CleanListDiffUtil<ItemClass>? = null,
        var itemTouchHelper: ItemTouchHelper? = null)
    : RecyclerView.Adapter<HolderClass>(), CleanListPresenter.ICleanListView<ItemClass> {

    var itemsList = mutableListOf<ItemClass>()


    override fun updateCleanList(items: List<ItemClass>) {

        if (diffUtil == null) {
            itemsList.clear()
            itemsList.addAll(items)
            notifyDataSetChanged()
        } else {

            diffUtil?.setLists(itemsList, items)
            val diffResult = DiffUtil.calculateDiff(diffUtil)

            itemsList.clear()
            itemsList.addAll(items)

            diffResult.dispatchUpdatesTo(this)
        }

    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {

        if (cleanListTouchCallback != null) {
            itemTouchHelper = ItemTouchHelper(cleanListTouchCallback)
            itemTouchHelper?.attachToRecyclerView(recyclerView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderClass {
        val view = LayoutInflater.from(parent.context).inflate(listItemLayoutResourceId, parent, false)

        val constructor: Constructor<HolderClass>
        val instance: HolderClass
        val method: Method

        constructor = entityClass.getConstructor(View::class.java)
        instance = constructor.newInstance(view)
        method = instance.javaClass.superclass.getMethod("setCallBacks", Function1::class.java, Function1::class.java, Function1::class.java, ICleanListItemHolderExtraCallbacks::class.java)
        method.invoke(instance, cleanListPresenter::onItemPressed, cleanListPresenter::onItemLongPressed, this@CleanListAdapter::onDragHandleLongPressed, cleanListPresenter)

        return instance
    }

    override fun onBindViewHolder(holder: HolderClass, position: Int) {

        holder.fillWithData(itemsList[holder.adapterPosition])
    }

    override fun getItemCount(): Int = itemsList.size


    fun onDragHandleLongPressed(viewHolder: RecyclerView.ViewHolder) {

        itemTouchHelper?.startDrag(viewHolder)
    }

}