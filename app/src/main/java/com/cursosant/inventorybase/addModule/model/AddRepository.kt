package com.cursosant.inventorybase.addModule.model

import com.cursosant.inventorybase.common.MyDataBase
import com.cursosant.inventorybase.entities.Product

class AddRepository {
    private val myDataBase by lazy { MyDataBase.getInstance() }

    fun addProduct(product: Product, callback: (isSuccess: Boolean) -> Unit){
        callback(myDataBase.add(product))
    }
}