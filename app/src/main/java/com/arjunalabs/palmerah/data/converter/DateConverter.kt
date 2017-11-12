package com.arjunalabs.palmerah.data.converter

import android.arch.persistence.room.TypeConverter
import java.util.*


/**
 * Created by bobbyprabowo on 12/11/17.
 */
class DateConverter {
    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        if (timestamp != null) {
            return Date(timestamp)
        }
        return null
    }

    @TypeConverter
    fun toTimestamp(date: Date?): Long? {
        if (date != null) {
            return date.time
        }
        return null
    }
}