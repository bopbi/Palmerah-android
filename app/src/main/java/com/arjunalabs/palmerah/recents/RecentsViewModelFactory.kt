package com.arjunalabs.palmerah.recents

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.arjunalabs.palmerah.data.FriendDAO

/**
 * Created by bobbyprabowo on 9/3/17.
 */

class RecentsViewModelFactory(private val friendDAO: FriendDAO) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass != null) {
            if (modelClass.isAssignableFrom(RecentsViewModel::class.java)) {
                return RecentsViewModel(friendDAO) as T
            }
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}