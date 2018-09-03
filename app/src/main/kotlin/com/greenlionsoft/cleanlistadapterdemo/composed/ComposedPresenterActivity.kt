package com.greenlionsoft.cleanlistadapterdemo.composed

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.greenlionsoft.cleanlist.lib.ComposedCleanListAdapter
import com.greenlionsoft.cleanlist.lib.ComposedCleanListTouchCallback
import com.greenlionsoft.cleanlistadapterdemo.DemoAsyncListDiffUtil
import com.greenlionsoft.cleanlistadapterdemo.DemoItem
import com.greenlionsoft.cleanlistadapterdemo.DemoItemHolder
import com.greenlionsoft.cleanlistadapterdemo.R
import kotlinx.android.synthetic.main.activity_main.*


class ComposedPresenterActivity: AppCompatActivity(), ComposedPresenter.IComposedPresenterView {


    val presenter = ComposedPresenter(this)

    var isFirstOnResume = true

    val listAdapter = ComposedCleanListAdapter<DemoItem, DemoItemHolder>(
            R.layout.list_item_demo,
            DemoItemHolder::class.java,
            presenter,
            DemoAsyncListDiffUtil()
    ).apply {
        cleanListTouchCallback = ComposedCleanListTouchCallback(presenter, true, true, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "ComposedPresenterActivity"

        demoRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        demoRecyclerView.adapter = listAdapter
    }

    override fun onResume() {
        super.onResume()
        if (isFirstOnResume) {
            isFirstOnResume = false
            presenter.onViewReady()
        }
    }

    override fun updateCleanList(items: List<DemoItem>) {
        listAdapter.updateCleanList(items)
    }

    override fun showProgress() {
        Toast.makeText(this, "Show Progress", Toast.LENGTH_SHORT).show()
    }

    override fun hideProgress() {
        Toast.makeText(this, "Hide Progress", Toast.LENGTH_SHORT).show()

    }

    override fun showItemPressedMesage(position: Int) {
        Toast.makeText(this, "$position pressed", Toast.LENGTH_SHORT).show()
    }

    override fun showItemLongPressedMessage(position: Int) {
        Toast.makeText(this, "$position long pressed", Toast.LENGTH_SHORT).show()
    }

    override fun showNamePressedMessage(position: Int) {
        Toast.makeText(this, "$position name pressed", Toast.LENGTH_SHORT).show()
    }

    override fun showAgePressedMessage(position: Int) {
        Toast.makeText(this, "$position age pressed", Toast.LENGTH_SHORT).show()
    }
}