package com.ytplayer.eaceves

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ApplicationsAdapter (context: Context, feedEntries: ArrayList<FeedEntry>):
    RecyclerView.Adapter<ApplicationsAdapter.ViewHolder>() {
    private var localContext: Context? = null
    private var localFeedEntries: ArrayList<FeedEntry>?=null

    init{
        localContext=context
        localFeedEntries=feedEntries
    }

    override fun onBindViewHolder(holder: ApplicationsAdapter.ViewHolder, position: Int) {
        val currentFeedEntry: FeedEntry= localFeedEntries!![position]
        holder.textName.text=currentFeedEntry.name
        holder.textDescription.text=currentFeedEntry.summary.take(250).plus("")
    }

    override fun getItemCount(): Int {
        return localFeedEntries?.size ?: 0
    }

    class ViewHolder(v: View): RecyclerView.ViewHolder(v){
        val textName: TextView =v.findViewById(R.id.text_title)
        val textDescription: TextView =v.findViewById(R.id.text_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApplicationsAdapter.ViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(localContext)
        val view: View =layoutInflater.inflate(R.layout.activity_view_repetible, parent, false)
        return ViewHolder(view)
    }
}