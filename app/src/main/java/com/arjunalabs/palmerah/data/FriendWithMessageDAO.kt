package com.arjunalabs.palmerah.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import android.arch.persistence.room.Transaction
import io.reactivex.Flowable

/**
 * Created by bobbyprabowo on 12/11/17.
 */
@Dao
interface FriendWithMessageDAO {

    @Transaction
    @Query("SELECT * from friends where lastMessageId > 0")
    fun getFriendWithLastMessages(): Flowable<List<FriendWithMessage>>

    @Transaction
    @Query("SELECT * from friends where id = :id")
    fun getFriendWithMessagesForFriendId(id: Int): Flowable<FriendWithMessage>
}