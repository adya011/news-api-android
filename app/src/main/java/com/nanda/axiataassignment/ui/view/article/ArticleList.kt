package com.nanda.axiataassignment.ui.view.article

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nanda.axiataassignment.data.model.Article
import com.nanda.axiataassignment.data.model.Source
import com.nanda.axiataassignment.ui.adapter.ArticleAdapter
import com.nanda.axiataassignment.ui.adapter.SourceAdapter

class ArticleList(context: Context, attributeSet: AttributeSet) :
    RecyclerView(context, attributeSet), ArticleAdapter.ArticleClickListener {

    private val layoutManager = LinearLayoutManager(context)
    private val adapter = ArticleAdapter(arrayListOf(), this)

    private var paginationTotalItemCount = 0
    private var paginationPage = 1
    private var paginationMaxPage = Int.MAX_VALUE

    var clickListener: ArticleAdapter.ArticleClickListener? = null
    var onLoadMoreItemListener: ((page: Int) -> Unit)? = null

    fun addData(list: List<Article>) {
        adapter.addData(list)
    }

    init {
        setLayoutManager(layoutManager)
        setAdapter(adapter)

        addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                var firstVisibleItems: Int? = null
                var visibleItemThreshold = 0

                firstVisibleItems = layoutManager.findFirstVisibleItemPosition()

                if (firstVisibleItems != null) {
                    visibleItemThreshold = firstVisibleItems
                }

                if (visibleItemCount + visibleItemThreshold >= totalItemCount) {
                    onLoadMoreItemListener?.invoke(paginationPage + 1)
                }
            }
        })
    }

    override fun onArticleSelected(url: String) {
        clickListener?.onArticleSelected(url)
    }
}