package com.cursosant.inventorybase.mainModule.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

// Configuration for android 14 sdk update (1)
//@Config(sdk = [24, 33])
// (2)
@Config(maxSdk = 33)
@RunWith(AndroidJUnit4::class)
class MainViewModelTest{
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    @Test
    fun `check welcome live data change its value`(){
        val mainViewModel = MainViewModel(ApplicationProvider.getApplicationContext())
        val observer = Observer<Boolean>{}
        try {
            mainViewModel.welcome.observeForever(observer)
            val result = mainViewModel.welcome.value
            assertThat(result, `is`(true))
        } finally {
            mainViewModel.welcome.removeObserver(observer)
        }
    }

}