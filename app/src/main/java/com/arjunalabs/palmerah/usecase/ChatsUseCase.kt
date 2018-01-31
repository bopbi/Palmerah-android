package com.arjunalabs.palmerah.usecase

import com.arjunalabs.palmerah.data.FriendWithMessage
import com.arjunalabs.palmerah.repository.ChatsRepository
import io.reactivex.ObservableTransformer
import io.reactivex.subjects.PublishSubject

/**
 * Created by bobbyprabowo on 02/01/18.
 */
class ChatsUseCase (private val chatsRepository: ChatsRepository) {

    private val sideEffectSubject : PublishSubject<ChatsUseCaseResult> = PublishSubject.create()

    val handleAction = ObservableTransformer<ChatsUseCaseAction, ChatsUseCaseResult> { action ->
        action.compose(loadFromDB) // currently we only have one use case, if multiple it will use observable merge
    }

    private fun createResult(friendWithMessages: List<FriendWithMessage>) : ChatsUseCaseResult {
        return ChatsUseCaseResult(false, null, friendWithMessages)
    }

    private val loadFromDB = ObservableTransformer<ChatsUseCaseAction, ChatsUseCaseResult> { action ->
        action.flatMap {
            chatsRepository
                    .getFriendWithLastMessages()
                    .doOnError {
                        sideEffectSubject.onNext(ChatsUseCaseResult(false,it, emptyList()))
                    }
                    .onErrorReturn { emptyList() }
                    .filter{ it.isNotEmpty() }
                    .map { createResult(it) }
        }
    }
 }