package com.nanda.axiataassignment.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.nanda.axiataassignment.R
import com.nanda.axiataassignment.ui.adapter.ArticleAdapter
import com.nanda.axiataassignment.ui.intent.ArticleIntent
import com.nanda.axiataassignment.ui.viewmodel.ArticleViewModel
import com.nanda.axiataassignment.ui.viewstate.ArticleState
import kotlinx.android.synthetic.main.fragment_article_list.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

const val ARG_SOURCE_ID = "source_id"

class ArticleListFragment : Fragment() {

    private val articleViewModel: ArticleViewModel by viewModel()
    private var articleAdapter = ArticleAdapter(arrayListOf())
    private var sourceId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            sourceId = it.getString(ARG_SOURCE_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_article_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        fetchNews()
        setObserver()
    }

    private fun setupView() {
        rvArticle.adapter = articleAdapter
    }

    private fun fetchNews() {
        lifecycleScope.launch {
            articleViewModel.userIntent.send(ArticleIntent.FetchArticle)
        }
    }

    private fun setObserver() {
        lifecycleScope.launch {
            articleViewModel.state.collect {
                when (it) {
                    is ArticleState.Idle -> {

                    }
                    is ArticleState.Loading -> {
                        Log.d("nandaDebug", "loading...")
                    }
                    is ArticleState.News -> {
                        Log.d("nandaDebug", "get data article: ${it.article.articles.size}")
                        articleAdapter.addData(it.article.articles)
                    }
                    is ArticleState.Error -> {
                        Log.d("nandaDebug", "error: ${it.error}")
                    }
                }
            }
        }
    }

    companion object {
        fun newInstance(sourceId: String) =
            ArticleListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_SOURCE_ID, sourceId)
                }
            }
    }
}