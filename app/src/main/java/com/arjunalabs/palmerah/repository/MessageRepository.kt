package com.arjunalabs.palmerah.repository

import com.arjunalabs.palmerah.data.MessageDAO

/**
 * Created by bobbyprabowo on 9/2/17.
 */

class MessageRepository(val messageDAO: MessageDAO) {

    fun loadMessagesFrom() = messageDAO.getAllMessages()
}