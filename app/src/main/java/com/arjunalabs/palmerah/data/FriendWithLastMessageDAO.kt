package com.arjunalabs.palmerah.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import io.reactivex.Flowable

/**
 * Created by bobbyprabowo on 12/11/17.
 */
@Dao
interface FriendWithLastMessageDAO {

    @Query("SELECT * from friends")
    fun getFriendLastWithMessages(): Flowable<List<FriendWithLastMessage>>

    @Query("SELECT * from friends where id = :id")
    fun getFriendWithLastMessagesForFriendId(id: Int): Flowable<FriendWithLastMessage>
}