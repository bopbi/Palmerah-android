package com.arjunalabs.palmerah.usecase

import com.arjunalabs.palmerah.data.FriendWithMessage

/**
 * Created by bobbyprabowo on 02/01/18.
 */
sealed class ChatsUseCaseResult {
    object loading : ChatsUseCaseResult()
    data class error(val error: Throwable) : ChatsUseCaseResult()
    data class data(val data: List<FriendWithMessage>) : ChatsUseCaseResult()
}