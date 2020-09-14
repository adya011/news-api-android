package com.nanda.axiataassignment.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nanda.axiataassignment.R
import com.nanda.axiataassignment.base.BaseHelper

class MainActivity : AppCompatActivity(), BaseHelper {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startFragment(
            this,
            R.id.fl_main, CategoryListFragment(),
            isAddToBackStack = false
        )
    }
}
