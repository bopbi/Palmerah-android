package com.arjunalabs.palmerah.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

/**
 * Created by bobbyprabowo on 9/2/17.
 */
@Entity(tableName = "messages")
data class Message(val friendId: Long, val text: String, val date: Long, val isSender: Boolean) {

    @PrimaryKey(autoGenerate = true) var id: Int = 0

}