package com.arjunalabs.palmerah.recents

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.arjunalabs.palmerah.R
import com.arjunalabs.palmerah.chatroom.ChatroomActivity
import com.arjunalabs.palmerah.data.Friend
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

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

    override fun onViewRecycled(holder: RecentsViewHolder?) {
        super.onViewRecycled(holder)
        holder?.unBind()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) =
        RecentsViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.row_recents, parent, false))

    override fun getItemCount() = friendList?.size ?: 0

    class RecentsViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        private var nameTextView : TextView? = itemView?.findViewById(R.id.textview_name_row_recents)
        private val recentsRowViewModel = RecentsRowViewModel()
        private val compositeDisposable = CompositeDisposable()

        init {
            itemView?.setOnClickListener {
                val chatRoomIntent = Intent(itemView.context, ChatroomActivity::class.java)
                itemView.context.startActivity(chatRoomIntent)
            }
        }


        fun bind(friend: Friend) {
            recentsRowViewModel.bind(friend)

            val nameDisposable = recentsRowViewModel.nameSubject.subscribe {
                nameTextView?.text = it
            }
            compositeDisposable.add(nameDisposable)
        }

        fun unBind() {
            recentsRowViewModel.unBind()
            compositeDisposable.clear()
        }

    }
}

