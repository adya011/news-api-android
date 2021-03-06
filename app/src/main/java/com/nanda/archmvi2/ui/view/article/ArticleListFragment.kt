package com.nanda.archmvi2.ui.view.article

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.nanda.archmvi2.R
import com.nanda.archmvi2.ui.adapter.ArticleAdapter
import com.nanda.archmvi2.ui.intent.ArticleIntent
import com.nanda.archmvi2.ui.view.WebViewActivity
import com.nanda.archmvi2.ui.viewmodel.ArticleViewModel
import com.nanda.archmvi2.ui.viewstate.ArticleState
import com.nanda.archmvi2.util.Constants
import com.nanda.archmvi2.util.gone
import com.nanda.archmvi2.util.visible
import kotlinx.android.synthetic.main.fragment_article_list.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

const val ARG_SOURCE_ID = "source_id"

class ArticleListFragment : Fragment(), ArticleAdapter.ArticleClickListener {

    private val articleViewModel: ArticleViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        articleViewModel.viewState = articleViewModel.viewState.copy(sourceId = getSourceId())
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
        fetchArticleIntent()
        handleIntent()
    }

    private fun setupView() {
        rvArticle.clickListener = this
        rvArticle.onLoadMoreItemListener = { page ->
            articleViewModel.viewState = articleViewModel.viewState.copy(page = page)
            fetchArticleIntent()
        }
    }

    private fun fetchArticleIntent() {
        lifecycleScope.launch {
            articleViewModel.userIntent.send(ArticleIntent.FetchArticle)
        }
    }

    private fun getSourceId(): String? {
        return arguments?.getString(ARG_SOURCE_ID)
    }

    private fun handleIntent() {
        lifecycleScope.launch {
            articleViewModel.state.collect {
                when (it) {
                    is ArticleState.Idle -> {
                        pbSources.gone()
                    }
                    is ArticleState.Loading -> {
                        pbSources.visible()
                    }
                    is ArticleState.Success -> {
                        pbSources.gone()
                        rvArticle.addData(it.article.articles)
                    }
                    is ArticleState.Error -> {
                        pbSources.gone()
                        Toast.makeText(
                            context,
                            "${Constants.error} : ${it.error}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    override fun onArticleSelected(url: String) {
        startActivity(Intent(requireActivity(), WebViewActivity::class.java).apply {
            putExtra(WebViewActivity.WEB_VIEW_URL, url)
        })

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