package com.greenlionsoft.cleanlistadapterdemo.extended

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import android.widget.Toast
import com.greenlionsoft.cleanlist.lib.CleanListAdapter
import com.greenlionsoft.cleanlist.lib.CleanListTouchCallback
import com.greenlionsoft.cleanlistadapterdemo.*
import kotlinx.android.synthetic.main.activity_main.*

class DemoActivity : AppCompatActivity(), DemoPresenter.IDemoView {

    val presenter = DemoPresenter(this)

    var isFirstOnResume = true

    val listAdapter = CleanListAdapter<DemoItem, DemoItemHolder>(
            R.layout.list_item_demo,
            DemoItemHolder::class.java,
            presenter,
            DemoAsyncListDiffUtil()
    ).apply {
        cleanListTouchCallback = CleanListTouchCallback(presenter, true, true, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Extended Clean List Presenter"

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
