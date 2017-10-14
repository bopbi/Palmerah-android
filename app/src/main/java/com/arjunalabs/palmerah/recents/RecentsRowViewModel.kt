package com.arjunalabs.palmerah.recents

import com.arjunalabs.palmerah.data.Friend
import io.reactivex.Observable
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

    fun bind(friend: Friend) {
       compositeDisposable.add(
               Observable.just(friend)
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe {
                 nameSubject.onNext(it.name)
               })
    }

    fun unBind() {
        compositeDisposable.clear()
    }

}