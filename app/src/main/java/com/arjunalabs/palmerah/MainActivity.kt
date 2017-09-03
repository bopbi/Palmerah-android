package com.arjunalabs.palmerah

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import com.arjunalabs.palmerah.contacts.ContactsFragment
import com.arjunalabs.palmerah.recents.RecentsFragment
import com.arjunalabs.palmerah.settings.SettingsFragment

class MainActivity : AppCompatActivity() {

    lateinit var bottomNavigation : BottomNavigationView
    lateinit var recentsFragment : RecentsFragment
    lateinit var contactsFragment : ContactsFragment
    lateinit var settingsFragment : SettingsFragment
    lateinit var selectedFragment : Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation_main_nav)

        recentsFragment = RecentsFragment()
        contactsFragment = ContactsFragment()
        settingsFragment = SettingsFragment()

        bottomNavigation.setOnNavigationItemSelectedListener{
            when (it.itemId) {
                R.id.menu_recents -> selectedFragment = recentsFragment
                R.id.menu_contacts -> selectedFragment = contactsFragment
                R.id.menu_settings -> selectedFragment = settingsFragment
            }
            supportFragmentManager.beginTransaction().replace(R.id.frame_main, selectedFragment).commitNow()
            true
        }

        bottomNavigation.selectedItemId = R.id.menu_recents
    }
}
