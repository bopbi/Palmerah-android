package com.arjunalabs.palmerah

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.bobby.rxwsocket.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * Created by bobbyadiprabowo on 7/23/17.
 */

class ChatRoomActivity : AppCompatActivity() {

    lateinit var rxDisposable : Disposable
    override fun onResume() {
        super.onResume()

        val request = Request.Builder()
                .get()
                .url("ws://echo.websocket.org/") // use echo websocket test server
                .build()

        val rxWSocket = RxWSocket(OkHttpClient(), request)
        rxDisposable = rxWSocket.webSocketObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    when (it) {
                        is RxWSOpenEvent -> {
                            println("Opened Flowable")
                            it.webSocket?.send("Send Ping")
                        }

                        is RxWSMessageStringEvent -> {
                            println("Receive Message String: " + it.text)
                        }

                        is RxWSMessageByteEvent -> {
                            println("Receive Message Byte: " + it.bytes)
                        }

                        is RxWSClosingEvent -> {
                            println("Closing")
                        }

                        is RxWSFailureEvent -> {
                            println("Failure")
                        }

                        is RxWSClosedEvent -> {
                            println("Closed")
                        }

                    }
                }
    }

    override fun onPause() {
        super.onPause()

        rxDisposable.dispose()
    }
}