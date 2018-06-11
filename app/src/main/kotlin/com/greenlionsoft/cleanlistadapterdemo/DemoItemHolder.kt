package com.greenlionsoft.cleanlistadapterdemo

import android.view.View
import com.greenlionsoft.cleanlist.lib.CleanListItemHolder
import com.greenlionsoft.cleanlist.mvp.ICleanListItemHolderExtraCallbacks
import kotlinx.android.synthetic.main.list_item_demo.view.*

/**
 *
 * Demo holder class that also defines 2 extra callbacks
 *
 */
class DemoItemHolder(itemView: View) : CleanListItemHolder<DemoItem>(itemView)   {

    override fun fillWithData(item: DemoItem) {

        itemView.nameTv.text = item.name
        itemView.ageTv.text = item.age.toString()

        itemView.nameTv.setOnClickListener {

            if (extraCallbacks != null) {
                (extraCallbacks as IDemoItemHolderExtraCallbacks).onNamePressed(adapterPosition)
            }
        }

        itemView.ageTv.setOnClickListener {

            if (extraCallbacks != null) {
                (extraCallbacks as IDemoItemHolderExtraCallbacks).onAgePressed(adapterPosition)
            }
        }

    }

    interface IDemoItemHolderExtraCallbacks: ICleanListItemHolderExtraCallbacks {

        fun onNamePressed(position: Int)

        fun onAgePressed(position: Int)

    }
}