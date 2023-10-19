package com.cursosant.inventorybase.addModule.view

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.fragment.app.testing.launchFragment
import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import com.cursosant.inventorybase.R
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.notNullValue
import org.hamcrest.Matchers.nullValue
import org.robolectric.annotation.Config

@Config(sdk = [24, 33])
@RunWith(AndroidJUnit4::class)
class AddProductFragmentTest{
    @Test
    fun `Check if the dialog is on the screen`(){
        val scenario = launchFragment<AddProductFragment>(themeResId = R.style.Theme_InventoryBase)
        scenario.moveToState(Lifecycle.State.RESUMED)
        scenario.onFragment{ fragment ->
            assertThat(fragment.dialog, `is`(notNullValue()))
        }
    }

    @Test
    fun `Check if after tap the cancel dialog button the fragment is null`(){
        val scenario = launchFragment<AddProductFragment>(themeResId = R.style.Theme_InventoryBase)
        scenario.moveToState(Lifecycle.State.RESUMED)
        scenario.onFragment{ fragment ->
            (fragment.dialog as? AlertDialog)?.let {
                it.getButton(DialogInterface.BUTTON_NEGATIVE).performClick()
                assertThat(fragment.dialog, `is`(nullValue()))
            }
        }
    }
}