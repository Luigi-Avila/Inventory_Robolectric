package com.cursosant.inventorybase.addModule.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cursosant.inventorybase.entities.Product
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AddViewModelTest{
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `value of result livedata changes`(){
        val addViewModel = AddViewModel()
        val product = Product(177, "Pixel", 200, "https://google/pixel/phone.jpg", 5.5, 100)
        //Create the observer
        val observer = Observer<Boolean>{}
        try {
            //Set observer
            addViewModel.result.observeForever(observer)
            //Call the function
            addViewModel.addProduct(product)
            //Get result
            val result = addViewModel.result.value
            //Validate result
            assertThat(result, `is`(true))
        } finally {
            //Remove the observer
            addViewModel.result.removeObserver(observer)
        }

    }
}