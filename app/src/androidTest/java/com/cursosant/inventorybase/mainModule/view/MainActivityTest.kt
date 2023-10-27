package com.cursosant.inventorybase.mainModule.view

import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.cursosant.inventorybase.R

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {
    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun actionBar_menuItemClick_returnMessage() {
        onView(withId(R.id.recyclerView)).perform(click())

        onView(withId(R.id.action_history)).perform(click())

        // For dynamic messages like different languages
        var snackBarMessage = ""
        activityScenarioRule.scenario.onActivity { activity ->
            snackBarMessage = activity.resources.getString(R.string.menu_msg_history)
        }

        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withText(snackBarMessage)))
    }

    @Test
    fun menuContext_actionExit_returnsMessage() {
        onView(withId(R.id.recyclerView)).perform(click())

        // Open Menu
        openActionBarOverflowOrOptionsMenu(ApplicationProvider.getApplicationContext())

        var actionTitle = ""
        var snackBarMessage = ""
        activityScenarioRule.scenario.onActivity { activity ->
            actionTitle = activity.resources.getString(R.string.main_menu_title_exit)
            snackBarMessage = activity.resources.getString(R.string.menu_msg_exit)
        }


        onView(withText(actionTitle)).perform(click())

        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withText(snackBarMessage)))
    }
}