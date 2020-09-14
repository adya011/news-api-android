package com.nanda.axiataassignment.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nanda.axiataassignment.R
import com.nanda.axiataassignment.data.model.NewsCategory
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryAdapter(
    private val categories: ArrayList<NewsCategory>,
    val listener: CategoryClickListener
) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    fun addData(list: List<NewsCategory>) {
        categories.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_category, parent, false
            )
        )

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(categories[position])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(category: NewsCategory) {
            with(itemView) {
                textViewCategoryName.text = category.name
                setOnClickListener {
                    listener.onCategorySelected(category.name)
                }

            }
        }
    }

    interface CategoryClickListener {
        fun onCategorySelected(categoryName: String)
    }
}