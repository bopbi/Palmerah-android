package com.arjunalabs.palmerah.usecase

/**
 * Created by bobbyprabowo on 03/01/18.
 */

sealed class ChatsUseCaseAction {
    object LoadFromDB : ChatsUseCaseAction()
}