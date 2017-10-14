package com.arjunalabs.palmerah

import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by bobbyprabowo on 9/13/17.
 */

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Before
    fun setup() {

    }

    @Test
    fun shouldDisplayBottomNavigationTabAndConsistOfThreeTab() {
        // given
        val bottomNavigation = withId(R.id.bottom_navigation_main_nav)

        // then
        EspressoVisibilityChecker.isViewDisplayed(bottomNavigation)


    }
}