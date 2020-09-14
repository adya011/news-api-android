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
import com.nanda.axiataassignment.ui.adapter.SourceAdapter
import com.nanda.axiataassignment.ui.intent.SourceIntent
import com.nanda.axiataassignment.ui.viewmodel.SourceViewModel
import com.nanda.axiataassignment.ui.viewstate.SourceState
import com.nanda.axiataassignment.ui.viewstate.SourceViewState
import kotlinx.android.synthetic.main.fragment_article_list.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import javax.xml.transform.Source

const val ARG_CATEGORY_NAME = "category_name"

class SourceListFragment : Fragment(), SourceAdapter.SourceClickListener, BaseHelper {

    private val sourceViewModel: SourceViewModel by viewModel()
    private var sourceAdapter = SourceAdapter(arrayListOf(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sourceViewModel.viewState = sourceViewModel.viewState.copy(category = getCategoryName())
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

    private fun getCategoryName(): String? {
        return arguments?.getString(ARG_CATEGORY_NAME)
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
                    is SourceState.Success -> {
                        Log.d("nandaDebug", "get data source: ${it.data.sources.size}")
                        sourceAdapter.addData(it.data.sources)
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
            SourceListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_CATEGORY_NAME, category)
                }
            }
    }
}