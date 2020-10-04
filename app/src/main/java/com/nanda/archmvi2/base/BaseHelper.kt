package com.nanda.archmvi2.base

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

interface BaseHelper {

    fun startFragment(
        activity: AppCompatActivity,
        @IdRes contentId: Int,
        fragment: Fragment,
        isAddToBackStack: Boolean = true,
        isAddFragment: Boolean = false
    ) {
        val transaction = activity.supportFragmentManager.beginTransaction()

        transaction.apply {
            when (isAddFragment) {
                true -> this.add(contentId, fragment)
                false -> this.replace(contentId, fragment)
            }
            if (isAddToBackStack) {
                addToBackStack(null)
            }
        }.commit()
    }
}