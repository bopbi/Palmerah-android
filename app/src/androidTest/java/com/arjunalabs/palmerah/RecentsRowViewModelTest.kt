package com.arjunalabs.palmerah

import android.support.test.runner.AndroidJUnit4
import com.arjunalabs.palmerah.data.Friend
import com.arjunalabs.palmerah.recents.RecentsRowViewModel
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.schedulers.Schedulers

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito


/**
 * Created by bobbyprabowo on 15/10/17.
 */
@RunWith(AndroidJUnit4::class)
class RecentsRowViewModelTest {

    @Test
    fun shouldDisplayName() {
        val viewModel = RecentsRowViewModel()
        val mockedName = "Bob"
        val friend = Friend(userId = 1, name = mockedName, avatarUrl = "", userStatus = "")

        val testSubscriber = viewModel.nameSubject.subscribeOn(Schedulers.trampoline())
                .observeOn(Schedulers.trampoline()).test()

        viewModel.bind(friend, Schedulers.trampoline(), Schedulers.trampoline())

        testSubscriber.assertValue(mockedName)
    }
}