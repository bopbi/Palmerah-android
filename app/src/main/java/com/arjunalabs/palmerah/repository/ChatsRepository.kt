package com.arjunalabs.palmerah.repository

import com.arjunalabs.palmerah.data.FriendWithMessage
import com.arjunalabs.palmerah.data.FriendWithMessageDAO
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by bobbyprabowo on 30/12/17.
 */
class ChatsRepository(private val subscriberScheduler: Scheduler,private val observerScheduler: Scheduler, private val friendWithMessageDAO: FriendWithMessageDAO) {

    constructor(friendWithMessageDAO: FriendWithMessageDAO) : this(Schedulers.io(), AndroidSchedulers.mainThread(), friendWithMessageDAO)

    fun getFriendWithLastMessages() : Observable<List<FriendWithMessage>> = friendWithMessageDAO
            .getFriendWithLastMessages()
            .toObservable()
            .subscribeOn(subscriberScheduler)
            .observeOn(observerScheduler)
}