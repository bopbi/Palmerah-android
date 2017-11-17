package com.arjunalabs.palmerah.chats

import android.arch.lifecycle.ViewModel
import com.arjunalabs.palmerah.data.Friend
import com.arjunalabs.palmerah.data.FriendDAO
import com.arjunalabs.palmerah.data.FriendWithLastMessage
import com.arjunalabs.palmerah.data.FriendWithLastMessageDAO
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by bobbyprabowo on 9/2/17.
 */

class ChatsViewModel @Inject constructor(private val friendWithLastMessageDAO: FriendWithLastMessageDAO) : ViewModel() {

    fun getAllFriend() = getAllFriend(Schedulers.computation(), AndroidSchedulers.mainThread())

    fun getAllFriend(subscriberScheduler: Scheduler, observerScheduler: Scheduler) : Flowable<List<FriendWithLastMessage>> = friendWithLastMessageDAO
            .getFriendLastWithMessages()
            .subscribeOn(subscriberScheduler)
            .observeOn(observerScheduler)

}