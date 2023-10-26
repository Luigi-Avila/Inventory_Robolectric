package com.cursosant.inventorybase.mainModule.view.adapters

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.PerformException
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.longClick
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItem
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollTo
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Test
import org.junit.runner.RunWith
import com.cursosant.inventorybase.R
import com.cursosant.inventorybase.mainModule.view.MainActivity
import org.hamcrest.Matchers.containsString
import org.junit.Assert.fail
import org.junit.Rule


@LargeTest
@RunWith(AndroidJUnit4::class)
class ProductAdapterTest {

    @get:Rule
    val mActivityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun listItem_click_successCheck() {
        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withText(R.string.main_msg_welcome)))

        onView(withId(R.id.recyclerView))
            .perform(actionOnItemAtPosition<ProductAdapter.ViewHolder>(1, click()))

        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withText("Queso")))
    }

    @Test
    fun listItem_click_removeFromView() {
        onView(withId(R.id.recyclerView))
            .perform(
                actionOnItem<ProductAdapter.ViewHolder>(
                    hasDescendant(withText(containsString("Papas"))), longClick()
                ),
                scrollTo<ProductAdapter.ViewHolder>(
                    hasDescendant(withText(containsString("Queso")))
                )
            )

        try {
            onView(withId(R.id.recyclerView))
                .perform(
                    scrollTo<ProductAdapter.ViewHolder>(
                        hasDescendant(withText(containsString("Papas")))
                    )
                )
            fail("Papas is on the list yet!!")
        } catch (e: Exception) {
            assert((e as? PerformException) != null)
        }
    }
}