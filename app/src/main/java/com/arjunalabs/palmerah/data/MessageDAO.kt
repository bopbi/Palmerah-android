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
interface MessageDAO {

    @Query("SELECT * FROM messages where id = :id")
    fun getMessageById(id: Int) : Flowable<Message>

    @Query("SELECT * from messages")
    fun getAllMessages() : Flowable<List<Message>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMessage(message : Message) : Long

    @Query("DELETE FROM messages")
    fun deleteAllMessages()
}