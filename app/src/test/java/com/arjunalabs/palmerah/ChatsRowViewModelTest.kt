package com.arjunalabs.palmerah

import com.arjunalabs.palmerah.chats.ChatsRowViewModel
import com.arjunalabs.palmerah.data.Friend
import com.arjunalabs.palmerah.data.FriendWithMessage
import com.arjunalabs.palmerah.data.Message
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.schedulers.Schedulers
import org.junit.Test

/**
 * Created by bobbyprabowo on 15/10/17.
 */

class ChatsRowViewModelTest {

    @Test
    fun shouldDisplayName() {
        val viewModel = ChatsRowViewModel()
        val mockedName = "Bob"
        val mockedAvatarUrl = "https://sample"
        val mockedFriend : Friend = mock()
        val mockedFriendWithLastMessage : FriendWithMessage = mock()
        val mockedLastMessage : Message = mock()
        whenever(mockedLastMessage.text).thenReturn("last message")
        val mockedMessages : List<Message> = listOf(mockedLastMessage)

        whenever(mockedFriendWithLastMessage.friend).thenReturn(mockedFriend)
        whenever(mockedFriendWithLastMessage.messages).thenReturn(mockedMessages)
        whenever(mockedFriendWithLastMessage.friend?.name).thenReturn(mockedName)
        whenever(mockedFriendWithLastMessage.friend?.avatarUrl).thenReturn(mockedAvatarUrl)

        val testSubscriber = viewModel.nameSubject.test()

        viewModel.bind(Schedulers.trampoline(), Schedulers.trampoline(), mockedFriendWithLastMessage)

        testSubscriber.assertValue(mockedName)
    }
}