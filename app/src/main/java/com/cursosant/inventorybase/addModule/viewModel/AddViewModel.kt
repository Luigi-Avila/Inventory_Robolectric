package com.cursosant.inventorybase.addModule.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cursosant.inventorybase.addModule.model.AddRepository
import com.cursosant.inventorybase.entities.Product


class AddViewModel : ViewModel() {
    private val repository = AddRepository()

    private val _inProgress = MutableLiveData<Boolean>()
    val inProgress: LiveData<Boolean> = _inProgress

    private val _result = MutableLiveData<Boolean>()
    val result: LiveData<Boolean> = _result

    fun addProduct(product: Product){
        _inProgress.value = true
        repository.addProduct(product){
            _inProgress.value = false
            _result.value = it
        }
    }
}