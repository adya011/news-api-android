package com.nanda.axiataassignment.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.nanda.axiataassignment.R
import com.nanda.axiataassignment.data.model.Source
import com.nanda.axiataassignment.util.Constants
import kotlinx.android.synthetic.main.item_source.view.*

class SourceAdapter(
    private val sources: ArrayList<Source>,
    val listener: SourceClickListener
) :
    RecyclerView.Adapter<SourceAdapter.ViewHolder>() {

    fun addData(list: List<Source>) {
        sources.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_source, parent, false
            )
        )

    override fun getItemCount(): Int = sources.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(sources[position])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(source: Source) {
            with(itemView) {
                textViewSourceName.text = source.name
                setOnClickListener {
                    source.id?.let { id ->
                        listener.onSourceSelected(id)
                    } ?: run {
                        Toast.makeText(context, Constants.source_id_missing, Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }

    interface SourceClickListener {
        fun onSourceSelected(sourceId: String)
    }
}