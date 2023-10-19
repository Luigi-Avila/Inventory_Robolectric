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