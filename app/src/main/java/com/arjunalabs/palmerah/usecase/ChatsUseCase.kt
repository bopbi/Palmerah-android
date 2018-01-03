package com.arjunalabs.palmerah.usecase

import com.arjunalabs.palmerah.data.FriendWithMessage
import com.arjunalabs.palmerah.repository.ChatsRepository
import com.arjunalabs.palmerah.usecase.ChatsUseCaseAction.LoadFromDB
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * Created by bobbyprabowo on 02/01/18.
 */
class ChatsUseCase (private val chatsRepository: ChatsRepository) {

    private val sideEffectSubject : PublishSubject<ChatsUseCaseResult> = PublishSubject.create()

    fun handleAction(action: ChatsUseCaseAction) : Observable<ChatsUseCaseResult> {
        return when (action) {
            is LoadFromDB -> loadFromDB()
        }
    }

    private fun loadFromDB() : Observable<ChatsUseCaseResult> {
        return chatsRepository
                .getFriendWithLastMessages()
                .doOnError {
                    sideEffectSubject.onNext(ChatsUseCaseResult.error(it))
                }
                .onErrorReturn { emptyList() }
                .filter{ it.isNotEmpty() }
                .map { createResult(it) }
    }

    private fun createResult(friendWithMessages: List<FriendWithMessage>) : ChatsUseCaseResult {
        return ChatsUseCaseResult.data(friendWithMessages)
    }
 }