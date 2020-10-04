package com.nanda.archmvi2.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nanda.archmvi2.R
import com.nanda.archmvi2.base.BaseHelper
import com.nanda.archmvi2.ui.view.category.CategoryListFragment

class MainActivity : AppCompatActivity(), BaseHelper {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startFragment(
            this,
            R.id.fl_main,
            CategoryListFragment(),
            isAddToBackStack = false
        )
    }
}
