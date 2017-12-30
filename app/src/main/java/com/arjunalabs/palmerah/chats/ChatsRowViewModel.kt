package com.arjunalabs.palmerah.chats

import com.arjunalabs.palmerah.data.FriendWithMessage
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

/**
 * Created by bobbyprabowo on 14/10/17.
 */
class ChatsRowViewModel {

    private val compositeDisposable = CompositeDisposable()

    val nameSubject : BehaviorSubject<String> = BehaviorSubject.create()
    val lastMessageSubject : BehaviorSubject<String> = BehaviorSubject.create()
    val avatarUrlSubject : BehaviorSubject<String> = BehaviorSubject.create()

    fun bind(friendWithMessage: FriendWithMessage) = bind(Schedulers.computation(), AndroidSchedulers.mainThread(), friendWithMessage)

    fun bind(subscriberScheduler: Scheduler, observerScheduler: Scheduler, friendWithMessage: FriendWithMessage) {

        compositeDisposable.add(
                Observable.just(friendWithMessage.friend?.name)
                        .subscribeOn(subscriberScheduler)
                        .observeOn(observerScheduler)
                        .subscribe {
                            if (it != null) {
                                nameSubject.onNext(it)
                            }
                        })

        compositeDisposable.add(
                Observable.just(friendWithMessage.messages[0].text)
                        .subscribeOn(subscriberScheduler)
                        .observeOn(observerScheduler)
                        .subscribe {
                            lastMessageSubject.onNext(it)
                        })

        compositeDisposable.add(
                Observable.just(friendWithMessage.friend?.avatarUrl)
                        .subscribeOn(subscriberScheduler)
                        .observeOn(observerScheduler)
                        .subscribe {
                            if (it != null) {
                                avatarUrlSubject.onNext(it)
                            }
                        })
    }

    fun unBind() {
        compositeDisposable.clear()
    }

}