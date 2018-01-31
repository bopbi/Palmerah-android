package com.arjunalabs.palmerah.di

import com.arjunalabs.palmerah.MainApplication
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton


/**
 * Created by bobbyprabowo on 08/11/17.
 */
@Singleton
@Component(modules = [(AndroidInjectionModule::class), (PalmerahModule::class), (RoomModule::class)])
interface PalmerahComponent : AndroidInjector<MainApplication>