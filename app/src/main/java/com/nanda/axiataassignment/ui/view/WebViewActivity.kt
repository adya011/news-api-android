package com.nanda.axiataassignment.ui.view

import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.nanda.axiataassignment.R
import com.nanda.axiataassignment.util.Constants
import kotlinx.android.synthetic.main.activity_web_view.*


class WebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        val url = intent.getStringExtra(WebViewActivity.WEB_VIEW_URL)

        wvArticle.loadUrl(url)
    }

    companion object {
        const val WEB_VIEW_URL = "WEB_VIEW_URL"
    }
}