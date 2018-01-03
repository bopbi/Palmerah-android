package com.arjunalabs.palmerah.chats

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arjunalabs.palmerah.R
import com.arjunalabs.palmerah.chats.ChatsFragmentIntent.InitialIntent
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.kotlin.autoDisposable
import dagger.android.support.AndroidSupportInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

/**
 * Created by bobbyprabowo on 9/2/17.
 */

class ChatsFragment : Fragment() {

    private lateinit var recyclerChats: RecyclerView
    private lateinit var chatsAdapter: ChatsAdapter
    private lateinit var viewModel : ChatsViewModel
    private val disposable = CompositeDisposable()
    private val chatIntentSubject : PublishSubject<ChatsFragmentIntent> = PublishSubject.create()

    @Inject
    lateinit var viewModelFactory : ChatsViewModelFactory

    private val scopeProvider by lazy { AndroidLifecycleScopeProvider.from(this) }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        AndroidSupportInjection.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ChatsViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val recentsView = inflater.inflate(R.layout.fragment_recents, container, false)

        if (recentsView != null) {
            recyclerChats = recentsView.findViewById(R.id.recycler_recents)
            chatsAdapter = ChatsAdapter()
            recyclerChats.adapter = chatsAdapter
            recyclerChats.layoutManager = LinearLayoutManager(activity)
        }

        return recentsView
    }

    override fun onStart() {
        super.onStart()
        viewModel.processIntent(chatIntentSubject)

        viewModel.states()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .autoDisposable(scopeProvider)
                .subscribe {
                    handleViewState(it)
                }
    }

    override fun onResume() {
        super.onResume()
        if (viewModel.viewState.data.isEmpty()) {
            chatIntentSubject.onNext(InitialIntent)
        } else {
            handleViewState(viewModel.viewState)
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

}