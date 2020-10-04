package com.nanda.archmvi2.ui.view.source

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.nanda.archmvi2.R
import com.nanda.archmvi2.base.BaseHelper
import com.nanda.archmvi2.ui.adapter.SourceAdapter
import com.nanda.archmvi2.ui.intent.SourceIntent
import com.nanda.archmvi2.ui.view.article.ArticleListFragment
import com.nanda.archmvi2.ui.viewmodel.SourceViewModel
import com.nanda.archmvi2.ui.viewstate.SourceState
import com.nanda.archmvi2.util.gone
import com.nanda.archmvi2.util.visible
import kotlinx.android.synthetic.main.fragment_source_list.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

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
        rvSource.adapter = sourceAdapter
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
                        pbArticle.gone()
                    }
                    is SourceState.Loading -> {
                        pbArticle.visible()
                    }
                    is SourceState.Success -> {
                        pbArticle.gone()
                        sourceAdapter.addData(it.data.sources)
                    }
                    is SourceState.Error -> {
                        pbArticle.gone()
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