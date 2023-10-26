package com.cursosant.inventorybase

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matcher

fun clickOnChild(id: Int): ViewAction {
    return object : ViewAction {
        override fun getDescription(): String = "Child on ViewHolder"

        override fun getConstraints(): Matcher<View> = ViewMatchers.withId(id)

        override fun perform(uiController: UiController?, view: View) {
            (view.findViewById(id) as View).performClick()
        }
    }
}