package com.arjunalabs.palmerah.chats

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arjunalabs.palmerah.R
import com.arjunalabs.palmerah.chats.ChatsFragmentIntent.InitialIntent
import com.arjunalabs.palmerah.chats.ChatsFragmentIntent.fabClickIntent
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.kotlin.autoDisposable
import dagger.android.support.AndroidSupportInjection
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

/**
 * Created by bobbyprabowo on 9/2/17.
 */

class ChatsFragment : Fragment() {

    private lateinit var fabChats: FloatingActionButton
    private lateinit var recyclerChats: RecyclerView
    private lateinit var chatsAdapter: ChatsAdapter
    private lateinit var viewModel : ChatsViewModel
    private val disposable = CompositeDisposable()
    private val chatIntentSubject : PublishSubject<ChatsFragmentIntent> = PublishSubject.create()

    @Inject
    lateinit var viewModelFactory : ChatsViewModelFactory

    private val scopeProvider by lazy { AndroidLifecycleScopeProvider.from(this) }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ChatsViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val recentsView = inflater.inflate(R.layout.fragment_recents, container, false)

        if (recentsView != null) {
            recyclerChats = recentsView.findViewById(R.id.recycler_chats)
            chatsAdapter = ChatsAdapter()
            recyclerChats.adapter = chatsAdapter
            recyclerChats.layoutManager = LinearLayoutManager(activity)

            fabChats = recentsView.findViewById(R.id.fab_chats)
        }

        return recentsView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.states()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .autoDisposable(scopeProvider)
                .subscribe {
                    handleViewState(it)
                }
        viewModel.processIntent(intents())
    }

    override fun onResume() {
        super.onResume()
        if (viewModel.viewState.data.isEmpty()) {
            chatIntentSubject.onNext(InitialIntent)
        }
    }

    override fun onStop() {
        super.onStop()
        disposable.clear()
    }

    fun handleViewState(viewState: ChatsFragmentViewState) {
        if (viewState.isError) {

        }

        if (viewState.isLoading) {

        }

        if (viewState.data.isNotEmpty()) {
            chatsAdapter.friendList = viewState.data
            chatsAdapter.notifyDataSetChanged()
        }
    }

    private fun fabClicks() : Observable<ChatsFragmentIntent> {
        return Observable.create { emit->
            fabChats.setOnClickListener {
                emit.onNext(fabClickIntent)
            }
        }
    }

    private fun intents() : Observable<ChatsFragmentIntent> {
        return Observable.merge(chatIntentSubject, fabClicks())
    }

}