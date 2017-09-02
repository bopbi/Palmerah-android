package com.arjunalabs.palmerah.recents

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arjunalabs.palmerah.R

/**
 * Created by bobbyprabowo on 9/2/17.
 */
class RecentsAdapter : RecyclerView.Adapter<RecentsAdapter.RecentsViewHolder>() {

    override fun onBindViewHolder(holder: RecentsViewHolder?, position: Int) {
        holder?.bind()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecentsViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.row_recents, parent, false)
        return RecentsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 0
    }

    class RecentsViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        fun bind() {

        }
    }
}

