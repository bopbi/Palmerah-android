package com.arjunalabs.palmerah.repository

import com.arjunalabs.palmerah.data.FriendDAO

/**
 * Created by bobbyprabowo on 9/2/17.
 */

class FriendRepository(val friendDAO: FriendDAO) {

    fun loadAllFriend() = friendDAO.getAllFriends()


}