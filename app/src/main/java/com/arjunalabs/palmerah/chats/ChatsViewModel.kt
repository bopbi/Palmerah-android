package com.arjunalabs.palmerah.chats

import android.arch.lifecycle.ViewModel
import com.arjunalabs.palmerah.repository.ChatsRepository
import javax.inject.Inject

/**
 * Created by bobbyprabowo on 9/2/17.
 */

class ChatsViewModel @Inject constructor(private val chatsRepository: ChatsRepository) : ViewModel() {

    fun getChatList() = chatsRepository.getAllFriend()

}