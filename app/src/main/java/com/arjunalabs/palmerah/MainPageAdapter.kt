package com.arjunalabs.palmerah

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.arjunalabs.palmerah.chats.ChatsFragment
import com.arjunalabs.palmerah.settings.SettingsFragment

/**
 * Created by bobbyprabowo on 03/01/18.
 */
class MainPageAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> ChatsFragment()
            1 -> SettingsFragment()
            else -> {
                Fragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

}