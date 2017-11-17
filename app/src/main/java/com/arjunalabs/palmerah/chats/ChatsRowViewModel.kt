package com.arjunalabs.palmerah.chats

import com.arjunalabs.palmerah.data.FriendWithLastMessage
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

    fun bind(friend: FriendWithLastMessage) = bind(Schedulers.io(), AndroidSchedulers.mainThread(), friend)

    fun bind(subscriberScheduler: Scheduler, observerScheduler: Scheduler, friend: FriendWithLastMessage) {

        compositeDisposable.add(
                Observable.just(friend.friend?.name)
                        .subscribeOn(subscriberScheduler)
                        .observeOn(observerScheduler)
                        .subscribe {
                            nameSubject.onNext(it)
                        })

        compositeDisposable.add(
                Observable.just(friend.messages.get(0).text)
                        .subscribeOn(subscriberScheduler)
                        .observeOn(observerScheduler)
                        .subscribe {
                            lastMessageSubject.onNext(it)
                        })

        compositeDisposable.add(
                Observable.just(friend.friend?.avatarUrl)
                        .subscribeOn(subscriberScheduler)
                        .observeOn(observerScheduler)
                        .subscribe {
                            avatarUrlSubject.onNext(it)
                        })
    }

    fun unBind() {
        compositeDisposable.clear()
    }

}