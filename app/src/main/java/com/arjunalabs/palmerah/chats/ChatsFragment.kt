package com.arjunalabs.palmerah.chats

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arjunalabs.palmerah.R
import dagger.android.support.AndroidSupportInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by bobbyprabowo on 9/2/17.
 */

class ChatsFragment : Fragment() {

    private lateinit var recyclerRecents : RecyclerView
    private lateinit var chatsAdapter: ChatsAdapter
    private lateinit var viewModel : ChatsViewModel
    private val disposable = CompositeDisposable()

    @Inject
    lateinit var viewModelFactory : ChatsViewModelFactory

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        AndroidSupportInjection.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ChatsViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val recentsView = inflater.inflate(R.layout.fragment_recents, container, false)

        if (recentsView != null) {
            recyclerRecents = recentsView.findViewById(R.id.recycler_recents)
            chatsAdapter = ChatsAdapter()
            recyclerRecents.adapter = chatsAdapter
            recyclerRecents.layoutManager = LinearLayoutManager(activity)
        }

        return recentsView
    }

    override fun onStart() {
        super.onStart()

        AndroidSupportInjection.inject(this)

        disposable.add(viewModel.getAllFriend()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    chatsAdapter.friendList = it
                    chatsAdapter.notifyDataSetChanged()
                }, {
                    error -> Log.e(TAG, "Unable to get recents", error)
                }))
    }

    override fun onStop() {
        super.onStop()
        disposable.clear()
    }

    companion object {
        val TAG = ChatsFragment::class.java.simpleName!!
    }

}