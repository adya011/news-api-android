package com.nanda.axiataassignment.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nanda.axiataassignment.R
import com.nanda.axiataassignment.data.model.Article
import com.nanda.axiataassignment.data.model.NewsCategory
import kotlinx.android.synthetic.main.item_article.view.*
import kotlinx.android.synthetic.main.item_category.view.*

class ArticleAdapter(private val articles: ArrayList<Article>) :
    RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    fun addData(list: List<Article>) {
        articles.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_article, parent, false
            )
        )

    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(articles[position])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(article: Article) {
            with(itemView) {
                textViewArticleName.text = article.title
            }
        }
    }
}