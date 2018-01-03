package com.arjunalabs.palmerah

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector : DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector

    private lateinit var bottomNavigation : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation = findViewById(R.id.bottom_navigation_main_nav)

        val mainPagerAdapter = MainPageAdapter(supportFragmentManager)
        val viewPager = findViewById<ViewPager>(R.id.pager_main)
        viewPager.adapter = mainPagerAdapter


        bottomNavigation.setOnNavigationItemSelectedListener{
            when (it.itemId) {
                R.id.menu_chats -> viewPager.currentItem = 0
                R.id.menu_settings -> viewPager.currentItem = 1
            }
            true
        }


        bottomNavigation.selectedItemId = R.id.menu_chats
    }
}
