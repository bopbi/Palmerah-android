package com.arjunalabs.palmerah.di

import android.content.Context
import com.arjunalabs.palmerah.data.FriendDAO
import com.arjunalabs.palmerah.db.AppDB
import com.arjunalabs.palmerah.recents.RecentsViewModelFactory
import dagger.Module
import dagger.Provides

/**
 * Created by bobbyprabowo on 08/11/17.
 */
@Module
class RoomModule(val context: Context) {


    @Provides
    fun provideFriendDAO() : FriendDAO = AppDB.getInstance(context)!!.friendDAO()

    @Provides
    fun provideRecentsViewModelFactory() = RecentsViewModelFactory(provideFriendDAO())
}