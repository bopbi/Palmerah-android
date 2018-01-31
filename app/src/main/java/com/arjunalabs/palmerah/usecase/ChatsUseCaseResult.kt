package com.arjunalabs.palmerah.usecase

import com.arjunalabs.palmerah.data.FriendWithMessage

/**
 * Created by bobbyprabowo on 02/01/18.
 */
data class ChatsUseCaseResult(val isLoading: Boolean, val error: Throwable?, val data: List<FriendWithMessage>)