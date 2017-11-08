package com.arjunalabs.palmerah.di

import android.content.Context
import com.arjunalabs.palmerah.MainActivity
import com.arjunalabs.palmerah.data.FriendDAO
import com.arjunalabs.palmerah.db.AppDB
import com.arjunalabs.palmerah.recents.RecentsFragment
import com.arjunalabs.palmerah.recents.RecentsViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

/**
 * Created by bobbyprabowo on 08/11/17.
 */
@Module
abstract class PalmerahModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity() : MainActivity

    @ContributesAndroidInjector
    abstract fun contributeRecentsFragment() : RecentsFragment
}