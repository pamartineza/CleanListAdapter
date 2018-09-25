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
import com.greenlionsoft.cleanlist.mvp.IComposedCleanListPresenter
import java.lang.reflect.Constructor
import java.lang.reflect.Method


class ComposedCleanListAdapter<ItemClass, HolderClass : CleanListItemHolder<ItemClass>>(
        val listItemLayoutResourceId: Int,
        val entityClass: Class<HolderClass>,
        val composedCleanListPresenter: IComposedCleanListPresenter,
        var asyncDiffUtil: CleanListAsyncDiffUtil<ItemClass>,
        var cleanListTouchCallback: ComposedCleanListTouchCallback? = null,
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
        method.invoke(instance, composedCleanListPresenter::onItemPressed, composedCleanListPresenter::onItemLongPressed, this@ComposedCleanListAdapter::onDragHandleLongPressed, composedCleanListPresenter)

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