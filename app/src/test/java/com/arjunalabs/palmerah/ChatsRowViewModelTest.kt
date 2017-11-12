package com.arjunalabs.palmerah

import com.arjunalabs.palmerah.chats.ChatsRowViewModel
import com.arjunalabs.palmerah.data.FriendWithLastMessage
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
        val mockedFriend : FriendWithLastMessage = mock()
        whenever(mockedFriend.friend?.name).thenReturn(mockedName)

        val testSubscriber = viewModel.nameSubject.subscribeOn(Schedulers.trampoline())
                .observeOn(Schedulers.trampoline()).test()

        viewModel.bind(mockedFriend, Schedulers.trampoline(), Schedulers.trampoline())

        testSubscriber.assertValue(mockedName)
    }
}