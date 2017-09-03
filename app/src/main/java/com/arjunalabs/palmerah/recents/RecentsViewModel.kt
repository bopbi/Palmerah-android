package com.arjunalabs.palmerah.recents

import android.arch.lifecycle.ViewModel
import com.arjunalabs.palmerah.data.Friend
import com.arjunalabs.palmerah.data.FriendDAO
import io.reactivex.Flowable

/**
 * Created by bobbyprabowo on 9/2/17.
 */

class RecentsViewModel(private val friendDAO: FriendDAO) : ViewModel() {

    fun getAllFriend() : Flowable<List<Friend>> = friendDAO.getAllFriends()

}