package com.arjunalabs.palmerah.di

import android.content.Context
import com.arjunalabs.palmerah.db.AppDB
import com.arjunalabs.palmerah.chats.ChatsViewModelFactory
import com.arjunalabs.palmerah.data.*
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
    fun provideMessageDAO() : MessageDAO = AppDB.getInstance(context)!!.messageDAO()

    @Provides
    fun provideLastMessageDAO() : LastMessageDAO = AppDB.getInstance(context)!!.lastMessageDAO()

    @Provides
    fun provideFriendWithMessagesDAO() : FriendWithMessageDAO = AppDB.getInstance(context)!!.friendWithMessageDAO()

    @Provides
    fun provideFriendWithLastMessagesDAO() : FriendWithLastMessageDAO = AppDB.getInstance(context)!!.friendWithLastMessageDAO()

    @Provides
    fun provideRecentsViewModelFactory() = ChatsViewModelFactory(provideFriendWithLastMessagesDAO())
}