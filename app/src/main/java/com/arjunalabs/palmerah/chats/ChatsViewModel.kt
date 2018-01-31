package com.arjunalabs.palmerah.chats

import android.arch.lifecycle.ViewModel
import com.arjunalabs.palmerah.usecase.ChatsUseCase
import com.arjunalabs.palmerah.usecase.ChatsUseCaseAction
import com.arjunalabs.palmerah.usecase.ChatsUseCaseAction.LoadFromDB
import com.arjunalabs.palmerah.usecase.ChatsUseCaseResult
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

/**
 * Created by bobbyprabowo on 9/2/17.
 */

class ChatsViewModel @Inject constructor(private val chatsUseCase: ChatsUseCase) : ViewModel() {

    private val chatsIntent : PublishSubject<ChatsFragmentIntent> = PublishSubject.create()
    var viewState : ChatsFragmentViewState = ChatsFragmentViewState(false, false, listOf())

    fun processIntent(intent: Observable<ChatsFragmentIntent>) {
        intent.subscribe(chatsIntent)
    }

    private fun handleIntent(intent: ChatsFragmentIntent) : ChatsUseCaseAction {
        return when (intent) {
            ChatsFragmentIntent.InitialIntent -> LoadFromDB
            ChatsFragmentIntent.FabClickIntent -> LoadFromDB
        }
    }


    fun states() : Observable<ChatsFragmentViewState> {
        return chatsIntent
                .map { handleIntent(it) }
                .compose(chatsUseCase.handleAction)
                .map { handleResult(it) }
                .replay(1)
                .autoConnect(0)
    }

    private fun handleResult(result: ChatsUseCaseResult) : ChatsFragmentViewState {
        return when {
            result.isLoading -> ChatsFragmentViewState(false, true, emptyList())
            result.error != null -> ChatsFragmentViewState(true, false, emptyList())
            result.data.isNotEmpty() -> ChatsFragmentViewState(false, false, result.data)
            else -> ChatsFragmentViewState(false, false, emptyList())
        }
    }



}