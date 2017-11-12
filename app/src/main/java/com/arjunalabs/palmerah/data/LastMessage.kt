package com.arjunalabs.palmerah.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.arjunalabs.palmerah.data.converter.DateConverter
import java.util.*

/**
 * Created by bobbyprabowo on 9/2/17.
 */
@Entity(tableName = "last_messages")
data class LastMessage(val friend_id: Long, val text: String, val date: Long, val isSender: Boolean) {

    @PrimaryKey(autoGenerate = true) var id: Int = 0

}