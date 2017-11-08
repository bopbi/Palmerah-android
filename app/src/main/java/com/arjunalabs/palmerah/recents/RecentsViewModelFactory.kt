package com.arjunalabs.palmerah.recents

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.arjunalabs.palmerah.data.FriendDAO
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by bobbyprabowo on 9/3/17.
 */
@Singleton
class RecentsViewModelFactory @Inject constructor(val friendDAO: FriendDAO) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass != null) {
            if (modelClass.isAssignableFrom(RecentsViewModel::class.java)) {
                return RecentsViewModel(friendDAO) as T
            }
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}