package com.greenlionsoft

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.greenlionsoft.cleanlistadapterdemo.composed.ComposedPresenterActivity
import com.greenlionsoft.cleanlistadapterdemo.extended.DemoActivity
import com.greenlionsoft.cleanlistadapterdemo.R
import kotlinx.android.synthetic.main.activity_selector.*


class SelectorActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selector)

        presenterBt.setOnClickListener { startActivity(Intent(this, DemoActivity::class.java)) }
        composedPresenterBt.setOnClickListener { startActivity(Intent(this, ComposedPresenterActivity::class.java)) }
    }
}