package com.arjunalabs.palmerah.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import io.reactivex.Flowable

/**
 * Created by bobbyprabowo on 12/11/17.
 */
@Dao
interface FriendWithMessageDAO {

    @Query("SELECT * from friends")
    fun getFriendWithMessages(): Flowable<List<FriendWithMessage>>

    @Query("SELECT * from friends where id = :id")
    fun getFriendWithMessagesForFriendId(id: Int): Flowable<FriendWithMessage>
}