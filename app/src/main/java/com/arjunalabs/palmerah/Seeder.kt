package com.arjunalabs.palmerah

import android.content.Context
import android.util.Log
import com.arjunalabs.palmerah.data.Friend
import com.arjunalabs.palmerah.db.AppDB
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by bobbyprabowo on 9/3/17.
 */
class Seeder(private val context: Context) {

    private fun before(appDB: AppDB) {
        appDB.friendDAO().deleteAllFriends()
    }

    fun seed() {
        val appDB = AppDB.getInstance(context)
        if (appDB != null) {

            val compositeDisposable = CompositeDisposable()
            compositeDisposable.add(Observable.fromCallable {

                before(appDB)
                (1..100)
                        .map {
                            Log.i("Seeder", "generate")
                            Friend(userId = it.toLong()
                                    , name = "Friend $it"
                                    , avatarUrl = "http://plchldr.co/i/480x480"
                                    , userStatus = "Dummy Status $it")
                        }
                        .forEach {
                            Log.i("Seeder", "insert")
                            appDB.friendDAO().insertFriend(it)
                        }
            }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe())

        }
    }
}