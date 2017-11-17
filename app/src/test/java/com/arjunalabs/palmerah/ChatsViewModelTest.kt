package com.arjunalabs.palmerah

import com.arjunalabs.palmerah.chats.ChatsViewModel
import com.arjunalabs.palmerah.data.FriendWithLastMessage
import com.arjunalabs.palmerah.data.FriendWithLastMessageDAO
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import org.junit.Test

/**
 * Created by bobbyprabowo on 15/11/17.
 */

class ChatsViewModelTest {

    @Test
    fun shouldGetAllFriend() {

        val mockedList = mutableListOf<FriendWithLastMessage>()
        (1..10).forEach {
            mockedList.add(mock())
        }
        val f = Flowable.just(mockedList.toList())

        val mockedFriendWithLastMessageDAO = mock<FriendWithLastMessageDAO> {
            on { getFriendLastWithMessages() } doReturn f
        }
        val viewModel = ChatsViewModel(mockedFriendWithLastMessageDAO)
        viewModel.getAllFriend(Schedulers.trampoline(), Schedulers.trampoline()).test().assertComplete()
    }
}