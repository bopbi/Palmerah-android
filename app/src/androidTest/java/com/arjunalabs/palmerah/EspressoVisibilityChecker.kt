package com.arjunalabs.palmerah

import android.support.test.espresso.Espresso
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.view.View
import org.hamcrest.Matcher

/**
 * Created by bobbyprabowo on 13/10/17.
 */

class EspressoVisibilityChecker {
    companion object {
        fun isViewDisplayed(viewMatcher: Matcher<View>) {
            Espresso.onView(viewMatcher).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }
    }
}