package com.arjunalabs.palmerah

import com.arjunalabs.palmerah.chats.ChatsViewModel
import com.arjunalabs.palmerah.data.FriendWithMessage
import com.arjunalabs.palmerah.data.FriendWithMessageDAO
import com.arjunalabs.palmerah.repository.ChatsRepository
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

        val mockedList = mutableListOf<FriendWithMessage>()
        (1..10).forEach {
            mockedList.add(mock())
        }
        val f = Flowable.just(mockedList.toList())

        val mockedFriendWithLastMessageDAO = mock<FriendWithMessageDAO> {
            on { getFriendWithLastMessages() } doReturn f
        }

        val mockedChatRepository = ChatsRepository(Schedulers.trampoline(), Schedulers.trampoline(), mockedFriendWithLastMessageDAO)
        val viewModel = ChatsViewModel(mockedChatRepository)
        viewModel.getChatList().test().assertComplete()
    }
}