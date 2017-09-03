package com.arjunalabs.palmerah

import android.content.Context
import com.arjunalabs.palmerah.data.FriendDAO
import com.arjunalabs.palmerah.db.AppDB
import com.arjunalabs.palmerah.recents.RecentsViewModelFactory

/**
 * Created by bobbyprabowo on 9/3/17.
 */

object Injection {

    fun provideFriendDAO(context: Context) : FriendDAO = AppDB.getInstance(context)!!.friendDAO()

    fun provideRecentsViewModelFactory(context: Context) = RecentsViewModelFactory(provideFriendDAO(context))
}