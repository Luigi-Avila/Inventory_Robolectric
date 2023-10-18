package com.cursosant.inventorybase.mainModule.view.adapters

import com.cursosant.inventorybase.entities.Product


interface OnClickListener {
    fun onClick(product: Product)
    fun onLongClick(product: Product)
}