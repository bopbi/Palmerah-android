package com.arjunalabs.palmerah

import android.support.test.runner.AndroidJUnit4
import com.arjunalabs.palmerah.data.Friend
import com.arjunalabs.palmerah.recents.RecentsRowViewModel
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler

import org.junit.Test
import org.junit.runner.RunWith


/**
 * Created by bobbyprabowo on 15/10/17.
 */
@RunWith(AndroidJUnit4::class)
class RecentsRowViewModelTest {

    @Test
    fun shouldDisplayName() {
        val viewModel = RecentsRowViewModel()
        val name = "Bob"
        val friend = Friend(name)

        val testSubscriber = viewModel.nameSubject.subscribeOn(Schedulers.trampoline())
                .observeOn(Schedulers.trampoline()).test()

        viewModel.bind(friend, Schedulers.trampoline(), Schedulers.trampoline())

        testSubscriber.assertValue(name)
    }
}