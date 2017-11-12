package com.arjunalabs.palmerah.chats

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.arjunalabs.palmerah.data.FriendDAO
import com.arjunalabs.palmerah.data.FriendWithLastMessageDAO
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by bobbyprabowo on 9/3/17.
 */
@Singleton
class ChatsViewModelFactory @Inject constructor(val friendWithLastMessageDAO: FriendWithLastMessageDAO) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChatsViewModel::class.java)) {
            return ChatsViewModel(friendWithLastMessageDAO) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}