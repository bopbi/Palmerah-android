package com.arjunalabs.palmerah.recents

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arjunalabs.palmerah.R

/**
 * Created by bobbyprabowo on 9/2/17.
 */

class RecentsFragment : Fragment() {

    lateinit var recyclerRecents : RecyclerView
    lateinit var viewModel : RecentsViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RecentsViewModel::class.java)

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val recentsView = inflater?.inflate(R.layout.fragment_recents, container, false)

        if (recentsView != null) {
            recyclerRecents = recentsView.findViewById<RecyclerView>(R.id.recycler_recents)
            recyclerRecents.adapter = RecentsAdapter()
            recyclerRecents.layoutManager = LinearLayoutManager(activity)
        }

        return recentsView
    }

}