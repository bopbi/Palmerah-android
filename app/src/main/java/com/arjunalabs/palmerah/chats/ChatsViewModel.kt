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
            is ChatsFragmentIntent.InitialIntent -> LoadFromDB
        }
    }


    fun states() : Observable<ChatsFragmentViewState> {
        return chatsIntent
                .map { handleIntent(it) }
                .flatMap {  chatsUseCase.handleAction(it) }
                .map { handleResult(it) }
    }

    private fun handleResult(result: ChatsUseCaseResult) : ChatsFragmentViewState {
        return when (result) {
            is ChatsUseCaseResult.loading -> {
                ChatsFragmentViewState(false, true, emptyList())
            }
            is ChatsUseCaseResult.error -> {
                ChatsFragmentViewState(true, false, emptyList())
            }
            is ChatsUseCaseResult.data -> {
                viewState = ChatsFragmentViewState(false, false, result.data)
                viewState
            }
        }
    }



}