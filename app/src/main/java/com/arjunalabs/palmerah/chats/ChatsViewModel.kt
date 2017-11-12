package com.arjunalabs.palmerah.chats

import android.arch.lifecycle.ViewModel
import com.arjunalabs.palmerah.data.Friend
import com.arjunalabs.palmerah.data.FriendDAO
import com.arjunalabs.palmerah.data.FriendWithLastMessage
import com.arjunalabs.palmerah.data.FriendWithLastMessageDAO
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by bobbyprabowo on 9/2/17.
 */

class ChatsViewModel @Inject constructor(val friendWithLastMessageDAO: FriendWithLastMessageDAO) : ViewModel() {

    fun getAllFriend() : Flowable<List<FriendWithLastMessage>> = friendWithLastMessageDAO.getFriendLastWithMessages()

}