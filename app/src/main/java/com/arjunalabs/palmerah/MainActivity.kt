package com.arjunalabs.palmerah

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import com.arjunalabs.palmerah.contacts.ContactsFragment
import com.arjunalabs.palmerah.recents.RecentsFragment
import com.arjunalabs.palmerah.settings.SettingsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigation : BottomNavigationView
    private lateinit var recentsFragment : RecentsFragment
    private lateinit var contactsFragment : ContactsFragment
    private lateinit var settingsFragment : SettingsFragment
    private lateinit var selectedFragment : Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation = findViewById(R.id.bottom_navigation_main_nav)

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
