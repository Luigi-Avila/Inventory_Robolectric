package com.cursosant.inventorybase.mainModule.view.adapters

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Test
import org.junit.runner.RunWith
import com.cursosant.inventorybase.R
import com.cursosant.inventorybase.mainModule.view.MainActivity
import org.junit.Rule


@LargeTest
@RunWith(AndroidJUnit4::class)
class ProductAdapterTest{

    @get:Rule
    val mActivityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun listItem_click_successCheck(){
        onView(withId(R.id.recyclerView))
            .perform(actionOnItemAtPosition<ProductAdapter.ViewHolder>(1, click()))
    }
}