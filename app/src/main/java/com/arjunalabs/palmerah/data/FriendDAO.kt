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
interface FriendDAO {

    @Query("SELECT * FROM friends where id = :id")
    fun getFriendById(id: Int) : Flowable<Friend>

    @Query("SELECT * from friends")
    fun getAllFriends() : Flowable<List<Friend>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFriend(friend : Friend) : Long

    @Query("DELETE FROM friends")
    fun deleteAllFriends()
}