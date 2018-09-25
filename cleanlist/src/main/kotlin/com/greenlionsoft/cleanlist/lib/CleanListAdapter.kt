package com.greenlionsoft.cleanlist.lib

import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.AdapterListUpdateCallback
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ItemTouchHelper
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
 * 3- Create asyncDiffUtil to handle list updates
 * 4- Create CleanListAdapter and provide all desired callbacks and itemTouchHelper
 *
 */
class CleanListAdapter<ItemClass, HolderClass : CleanListItemHolder<ItemClass>>(
        val listItemLayoutResourceId: Int,
        val entityClass: Class<HolderClass>,
        val cleanListPresenter: CleanListPresenter<ItemClass>,
        var asyncDiffUtil: CleanListAsyncDiffUtil<ItemClass>,
        var cleanListTouchCallback: CleanListTouchCallback<ItemClass>? = null,
        var itemTouchHelper: ItemTouchHelper? = null)
    : RecyclerView.Adapter<HolderClass>(), CleanListPresenter.ICleanListView<ItemClass> {

    val asyncListDiffer = AsyncListDiffer<ItemClass>(
        AdapterListUpdateCallback(this),
        AsyncDifferConfig.Builder<ItemClass>(asyncDiffUtil).build()
    )

    override fun updateCleanList(items: List<ItemClass>) {

        asyncListDiffer.submitList(items.toList())
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {

        cleanListTouchCallback?.let {
            itemTouchHelper = ItemTouchHelper(it)
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

        holder.fillWithData(asyncListDiffer.currentList[holder.adapterPosition])
    }

    override fun getItemCount(): Int = asyncListDiffer.currentList.size


    fun onDragHandleLongPressed(viewHolder: RecyclerView.ViewHolder) {

        itemTouchHelper?.startDrag(viewHolder)
    }

}