package com.arjunalabs.palmerah

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.arjunalabs.palmerah.data.Friend
import com.arjunalabs.palmerah.db.AppDB
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by bobbyadiprabowo on 7/23/17.
 */

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(ScheduleActivityLifecycleCallback())

        // insert seeder
        Seeder(this).seed()

    }

    /**
     * Detecting android go to foreground
     * technique from : https://youtu.be/AJqakuas_6g?t=1704
     */
    class ScheduleActivityLifecycleCallback : ActivityLifecycleCallbacks {

        var numStarted = 0

        override fun onActivityPaused(p0: Activity?) {

        }

        override fun onActivityResumed(p0: Activity?) {

        }

        override fun onActivityStarted(p0: Activity?) {
            if (numStarted == 0) {

            }
            numStarted++
        }

        override fun onActivityDestroyed(p0: Activity?) {

        }

        override fun onActivitySaveInstanceState(p0: Activity?, p1: Bundle?) {

        }

        override fun onActivityStopped(p0: Activity?) {
            numStarted--
            if (numStarted == 0) {
                // it goes to background
            }
        }

        override fun onActivityCreated(p0: Activity?, p1: Bundle?) {

        }

    }
}