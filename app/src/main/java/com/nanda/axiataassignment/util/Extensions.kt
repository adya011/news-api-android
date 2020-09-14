package com.nanda.axiataassignment.util

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide


fun AppCompatImageView.loadImage(uri: String, icPlaceholder: Int) =
    Glide.with(this.context.applicationContext)
        .load(uri).placeholder(icPlaceholder)
        .into(this)

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}