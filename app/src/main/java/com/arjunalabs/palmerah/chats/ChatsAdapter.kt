package com.arjunalabs.palmerah.chats

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.arjunalabs.palmerah.R
import com.arjunalabs.palmerah.chatroom.ChatroomActivity
import com.arjunalabs.palmerah.data.Friend
import com.arjunalabs.palmerah.data.FriendWithLastMessage
import com.bumptech.glide.Glide
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

/**
 * Created by bobbyprabowo on 9/2/17.
 */
class ChatsAdapter : RecyclerView.Adapter<ChatsAdapter.RecentsViewHolder>() {

    var friendList : List<FriendWithLastMessage>? = null

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
        private var lastMessageTextView : TextView? = itemView?.findViewById(R.id.textview_last_message_row_recents)
        private var avatarImageView : ImageView? = itemView?.findViewById(R.id.image_row_recents)
        private val chatsRowViewModel = ChatsRowViewModel()
        private val compositeDisposable = CompositeDisposable()
        private var friend : FriendWithLastMessage? = null

        init {
            itemView?.setOnClickListener {
                val chatRoomIntent = Intent(itemView.context, ChatroomActivity::class.java)

                chatRoomIntent.extras.putString("FRIEND","")
                itemView.context.startActivity(chatRoomIntent)
            }
        }


        fun bind(friend: FriendWithLastMessage) {

            this.friend = friend
            nameTextView?.text = "Loading..."

            chatsRowViewModel.nameSubject.subscribe {
                nameTextView?.text = it
            }.addTo(compositeDisposable)

            chatsRowViewModel.lastMessageSubject.subscribe {
                lastMessageTextView?.text = it
            }.addTo(compositeDisposable)

            chatsRowViewModel.avatarUrlSubject.subscribe {
                Glide.with(avatarImageView).load(it).into(avatarImageView)
            }.addTo(compositeDisposable)

            chatsRowViewModel.bind(friend, Schedulers.computation(), AndroidSchedulers.mainThread())
        }

        fun unBind() {
            chatsRowViewModel.unBind()
            compositeDisposable.clear()
        }

    }
}

