package com.arjunalabs.palmerah.chats

import com.arjunalabs.palmerah.data.FriendWithMessage

/**
 * Created by bobbyprabowo on 02/01/18.
 */
data class ChatsFragmentViewState(val isError: Boolean, val isLoading: Boolean, val data: List<FriendWithMessage>)