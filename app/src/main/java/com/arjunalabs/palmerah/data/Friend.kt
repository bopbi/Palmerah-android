package com.arjunalabs.palmerah.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by bobbyprabowo on 9/2/17.
 */

@Entity(tableName = "friends")
data class Friend(
        val userId: Long,
        val name: String,
        val avatarUrl: String,
        val userStatus: String) {

    @PrimaryKey(autoGenerate = true) var id: Int = 0
}
//
//data class Friend(
//        @PrimaryKey(autoGenerate = true) val _id: Int,
//        @Embedded val messages: List<Message>,
//        @Embedded var lastMessage: Message)