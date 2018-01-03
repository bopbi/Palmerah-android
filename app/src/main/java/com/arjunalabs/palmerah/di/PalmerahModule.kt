package com.arjunalabs.palmerah.di

import com.arjunalabs.palmerah.MainActivity
import com.arjunalabs.palmerah.chats.ChatsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by bobbyprabowo on 08/11/17.
 */
@Module
abstract class PalmerahModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity() : MainActivity

    @ContributesAndroidInjector
    abstract fun contributeChatsFragment() : ChatsFragment
}