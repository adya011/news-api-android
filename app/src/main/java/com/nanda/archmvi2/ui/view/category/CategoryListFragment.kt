package com.nanda.archmvi2.ui.view.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.nanda.archmvi2.R
import com.nanda.archmvi2.base.BaseHelper
import com.nanda.archmvi2.data.model.NewsCategory
import com.nanda.archmvi2.ui.adapter.CategoryAdapter
import com.nanda.archmvi2.ui.view.source.SourceListFragment
import com.nanda.archmvi2.util.Constants
import kotlinx.android.synthetic.main.fragment__category.*

class CategoryListFragment : Fragment(),
    CategoryAdapter.CategoryClickListener,
    BaseHelper {

    private var categoryAdapter = CategoryAdapter(arrayListOf(), this)

    private val categoryList: List<NewsCategory> =
        listOf(
            NewsCategory(Constants.entertainment),
            NewsCategory(Constants.general),
            NewsCategory(Constants.health),
            NewsCategory(Constants.science),
            NewsCategory(Constants.sports),
            NewsCategory(Constants.technology)
        )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment__category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        rvCategory.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = categoryAdapter
        }
        categoryAdapter.addData(categoryList)
    }

    override fun onCategorySelected(categoryName: String) {
        startFragment(
            requireActivity() as AppCompatActivity,
            R.id.fl_main,
            SourceListFragment.newInstance(
                categoryName
            ),
            isAddFragment = true
        )
    }
}