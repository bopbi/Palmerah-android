package com.arjunalabs.palmerah.recents

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.arjunalabs.palmerah.R
import com.arjunalabs.palmerah.data.Friend

/**
 * Created by bobbyprabowo on 9/2/17.
 */
class RecentsAdapter : RecyclerView.Adapter<RecentsAdapter.RecentsViewHolder>() {

    var friendList : List<Friend>? = null

    override fun onBindViewHolder(holder: RecentsViewHolder?, position: Int) {
        if (friendList != null) {
            holder?.bind(friendList!![position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecentsViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.row_recents, parent, false)
        return RecentsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return friendList?.size ?: 0
    }

    class RecentsViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        private var nameTextView : TextView? = itemView?.findViewById(R.id.textview_name_row_recents)

        fun bind(friend: Friend) {
            nameTextView!!.text = friend.name
        }
    }
}

