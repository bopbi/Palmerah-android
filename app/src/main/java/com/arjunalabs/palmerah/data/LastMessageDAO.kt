package com.arjunalabs.palmerah.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Flowable

/**
 * Created by bobbyprabowo on 9/3/17.
 */
@Dao
interface LastMessageDAO {

    @Query("SELECT * FROM last_messages where id = :id")
    fun getLastMessageById(id: Int) : Flowable<LastMessage>

    @Query("SELECT * from last_messages")
    fun getAllLastMessages() : Flowable<List<LastMessage>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLastMessage(message : LastMessage) : Long

    @Query("DELETE FROM last_messages")
    fun deleteAllLastMessages()
}