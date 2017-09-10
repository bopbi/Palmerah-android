package com.arjunalabs.palmerah.recents

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arjunalabs.palmerah.Injection
import com.arjunalabs.palmerah.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by bobbyprabowo on 9/2/17.
 */

class RecentsFragment : Fragment() {

    private lateinit var recyclerRecents : RecyclerView
    private lateinit var recentsAdapter : RecentsAdapter
    private lateinit var viewModel : RecentsViewModel
    private val disposable = CompositeDisposable()


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModelFactory = Injection.provideRecentsViewModelFactory(activity)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(RecentsViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val recentsView = inflater?.inflate(R.layout.fragment_recents, container, false)

        if (recentsView != null) {
            recyclerRecents = recentsView.findViewById<RecyclerView>(R.id.recycler_recents)
            recentsAdapter = RecentsAdapter()
            recyclerRecents.adapter = recentsAdapter
            recyclerRecents.layoutManager = LinearLayoutManager(activity)
        }

        return recentsView
    }

    override fun onStart() {
        super.onStart()

        disposable.add(viewModel.getAllFriend()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    recentsAdapter.friendList = it
                    recentsAdapter.notifyDataSetChanged()
                }, {
                    error -> Log.e(TAG, "Unable to get recents", error)
                }))
    }

    override fun onStop() {
        super.onStop()
        disposable.clear()
    }

    companion object {
        val TAG = RecentsFragment::class.java.simpleName!!
    }

}