package com.nanda.archmvi2.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nanda.archmvi2.R
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