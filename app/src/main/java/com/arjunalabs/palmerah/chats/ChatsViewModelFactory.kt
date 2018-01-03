package com.arjunalabs.palmerah.chats

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.arjunalabs.palmerah.repository.ChatsRepository
import com.arjunalabs.palmerah.usecase.ChatsUseCase
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by bobbyprabowo on 9/3/17.
 */
@Singleton
class ChatsViewModelFactory @Inject constructor(private val chatsUseCase: ChatsUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChatsViewModel::class.java)) {
            return ChatsViewModel(chatsUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}