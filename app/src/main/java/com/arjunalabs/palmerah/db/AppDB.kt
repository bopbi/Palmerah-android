package com.arjunalabs.palmerah.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.arjunalabs.palmerah.data.Friend
import com.arjunalabs.palmerah.data.FriendDAO
import com.arjunalabs.palmerah.data.MessageDAO

/**
 * Created by bobbyprabowo on 9/2/17.
 */
@Database(entities = arrayOf(Friend::class), version = 2)
abstract class AppDB : RoomDatabase() {

    // abstract fun messageDAO() : MessageDAO
    abstract fun friendDAO() : FriendDAO

    companion object {

        @Volatile private var INSTANCE : AppDB? = null

        fun getInstance(context: Context) : AppDB? {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = buildDatabase(context)
                }
            }
            return INSTANCE
        }

        private fun buildDatabase(context: Context) : AppDB {
            return Room.databaseBuilder(context.applicationContext, AppDB::class.java, "app.db").build()
        }
    }

}