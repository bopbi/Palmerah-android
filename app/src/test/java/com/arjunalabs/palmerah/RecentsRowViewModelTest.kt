package com.arjunalabs.palmerah

import com.arjunalabs.palmerah.data.Friend
import com.arjunalabs.palmerah.recents.RecentsRowViewModel
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.schedulers.Schedulers
import org.junit.Test

/**
 * Created by bobbyprabowo on 15/10/17.
 */

class RecentsRowViewModelTest {

    @Test
    fun shouldDisplayName() {
        val viewModel = RecentsRowViewModel()
        val mockedName = "Bob"
        val mockedFriend : Friend = mock()
        whenever(mockedFriend.name).thenReturn(mockedName)

        val testSubscriber = viewModel.nameSubject.subscribeOn(Schedulers.trampoline())
                .observeOn(Schedulers.trampoline()).test()

        viewModel.bind(mockedFriend, Schedulers.trampoline(), Schedulers.trampoline())

        testSubscriber.assertValue(mockedName)
    }
}