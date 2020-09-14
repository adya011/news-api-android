package com.nanda.axiataassignment.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.nanda.axiataassignment.R
import com.nanda.axiataassignment.base.BaseHelper
import com.nanda.axiataassignment.data.model.Article
import com.nanda.axiataassignment.ui.adapter.SourceAdapter
import com.nanda.axiataassignment.ui.intent.ArticleIntent
import com.nanda.axiataassignment.ui.intent.SourceIntent
import com.nanda.axiataassignment.ui.viewmodel.SourceViewModel
import com.nanda.axiataassignment.ui.viewstate.SourceState
import kotlinx.android.synthetic.main.fragment_article_list.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

const val ARG_CATEGORY_NAME = "category_name"

class SourceListFragment : Fragment(), SourceAdapter.SourceClickListener, BaseHelper {

    private val sourceViewModel: SourceViewModel by viewModel()
    private var sourceAdapter = SourceAdapter(arrayListOf(), this)
    private var categoryName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            categoryName = it.getString(ARG_CATEGORY_NAME)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_source_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        fetchNews()
        setObserver()
    }

    private fun setupView() {
        rvArticle.adapter = sourceAdapter
    }

    private fun fetchNews() {
        lifecycleScope.launch {
            sourceViewModel.userIntent.send(SourceIntent.FetchSources)
        }
    }

    private fun setObserver() {
        lifecycleScope.launch {
            sourceViewModel.state.collect {
                when (it) {
                    is SourceState.Idle -> {
                        Log.d("nandaDebug", "idle")
                    }
                    is SourceState.Loading -> {
                        Log.d("nandaDebug", "loading...")
                    }
                    is SourceState.News -> {
                        Log.d("nandaDebug", "get data source: ${it.news.sources.size}")
                        sourceAdapter.addData(it.news.sources)
                    }
                    is SourceState.Error -> {
                        Log.d("nandaDebug", "error: ${it.error}")
                    }
                }
            }
        }
    }

    override fun onSourceSelected(sourceId: String) {
        startFragment(
            requireActivity() as AppCompatActivity,
            R.id.fl_main, ArticleListFragment.newInstance(sourceId),
            isAddFragment = true
        )
    }

    companion object {
        fun newInstance(category: String) =
            ArticleListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_CATEGORY_NAME, category)
                }
            }
    }
}