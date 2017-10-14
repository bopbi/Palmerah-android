package com.arjunalabs.palmerah

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.arjunalabs.palmerah.chatroom.ChatroomActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by bobbyprabowo on 14/10/17.
 */
@RunWith(AndroidJUnit4::class)
class ChatRoomActivityTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule<ChatroomActivity>(ChatroomActivity::class.java)

    @Test
    fun shouldRecyclerViewIsDisplayed() {

    }
}