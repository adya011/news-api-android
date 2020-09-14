package com.nanda.axiataassignment.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nanda.axiataassignment.R
import com.nanda.axiataassignment.data.model.Article
import com.nanda.axiataassignment.util.loadImage
import kotlinx.android.synthetic.main.item_article.view.*

class ArticleAdapter(
    private val articles: ArrayList<Article>,
    val listener: ArticleClickListener
) :
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
                article.urlToImage?.let {
                    ivArticle.loadImage(article.urlToImage, R.mipmap.ic_launcher)
                }
                tvArticleTitle.text = article.title
                tvDescription.text = article.content
                article.url?.let { url ->
                    setOnClickListener {
                        listener.onArticleSelected(url)
                    }
                }
            }
        }
    }

    interface ArticleClickListener {
        fun onArticleSelected(url: String)
    }
}