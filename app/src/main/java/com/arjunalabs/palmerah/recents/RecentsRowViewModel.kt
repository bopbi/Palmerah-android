package com.arjunalabs.palmerah.recents

import com.arjunalabs.palmerah.data.Friend
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

/**
 * Created by bobbyprabowo on 14/10/17.
 */
class RecentsRowViewModel {

    private val compositeDisposable = CompositeDisposable()

    val nameSubject : PublishSubject<String> = PublishSubject.create()

    fun bind(friend: Friend, subscriberScheduler: Scheduler, observerScheduler: Scheduler) {

       compositeDisposable.add(
               Observable.just(friend)
               .subscribeOn(subscriberScheduler)
               .observeOn(observerScheduler)
               .subscribe {
                   nameSubject.onNext(it.name)
               })
    }

    fun unBind() {
        compositeDisposable.clear()
    }

}