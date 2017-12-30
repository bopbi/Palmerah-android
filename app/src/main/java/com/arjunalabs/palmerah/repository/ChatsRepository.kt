package com.arjunalabs.palmerah.repository

import com.arjunalabs.palmerah.data.FriendWithMessage
import com.arjunalabs.palmerah.data.FriendWithMessageDAO
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by bobbyprabowo on 30/12/17.
 */
class ChatsRepository(private val subscriberScheduler: Scheduler,private val observerScheduler: Scheduler, private val friendWithMessageDAO: FriendWithMessageDAO) {

    constructor(friendWithMessageDAO: FriendWithMessageDAO) : this(Schedulers.io(), AndroidSchedulers.mainThread(), friendWithMessageDAO)

    fun getAllFriend() : Flowable<List<FriendWithMessage>> = friendWithMessageDAO
            .getFriendWithLastMessages()
            .subscribeOn(subscriberScheduler)
            .observeOn(observerScheduler)
}