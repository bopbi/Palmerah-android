package com.arjunalabs.palmerah.data

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation

/**
 * Created by bobbyprabowo on 12/11/17.
 */

class FriendWithMessage {

    @Embedded
    var friend : Friend? = null

    @Relation(parentColumn = "id", entityColumn = "friendId")
    var messages: List<Message> = listOf()
}