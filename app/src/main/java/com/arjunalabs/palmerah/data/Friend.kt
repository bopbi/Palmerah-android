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
        val userStatus: String,
        var lastMessageId: Long) {

    @PrimaryKey(autoGenerate = true) var id: Long = 0
}