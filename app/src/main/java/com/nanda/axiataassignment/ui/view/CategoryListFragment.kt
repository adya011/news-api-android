package com.nanda.axiataassignment.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.nanda.axiataassignment.R
import com.nanda.axiataassignment.base.BaseHelper
import com.nanda.axiataassignment.data.model.NewsCategory
import com.nanda.axiataassignment.ui.adapter.CategoryAdapter
import com.nanda.axiataassignment.util.Constants
import kotlinx.android.synthetic.main.fragment__category.*

class CategoryListFragment : Fragment(),
    CategoryAdapter.CategoryClickListener,
    BaseHelper {

    private var categoryAdapter = CategoryAdapter(arrayListOf(), this)

    private val categoryList: List<NewsCategory> =
        listOf(
            NewsCategory(1, Constants.entertainment),
            NewsCategory(2, Constants.general),
            NewsCategory(3, Constants.health),
            NewsCategory(4, Constants.science),
            NewsCategory(5, Constants.sports)
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
            R.id.fl_main, SourceListFragment(),
            isAddFragment = true
        )
    }
}