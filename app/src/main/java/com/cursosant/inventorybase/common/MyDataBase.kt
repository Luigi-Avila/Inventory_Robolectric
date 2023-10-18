package com.cursosant.inventorybase.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cursosant.inventorybase.entities.Product
import kotlin.random.Random


class MyDataBase {
    companion object{
        private var INSTANCE: MyDataBase? = null

        fun getInstance() = INSTANCE ?: synchronized(this){
            MyDataBase().also { INSTANCE = it }
        }
    }

    private val products = mutableListOf<Product>()
    private val productsLiveData: MutableLiveData<MutableList<Product>> = MutableLiveData()

    fun getProductsLiveData(): LiveData<MutableList<Product>> {
        if (products.size == 0){
            (0..Random.nextInt(1, names.size)).forEach {
                products.add(
                    Product(
                        id = it.toLong(),
                        name = names[it],
                        quantity = Random.nextInt(1, 999),
                        photoUrl = urls[it],
                        score = Random.nextDouble(2.5, 5.0),
                        totalVotes = Random.nextLong(1, 10)
                    )
                )
            }
        }
        val resultList = mutableListOf<Product>()
        resultList.addAll(products)
        productsLiveData.value = resultList
        return productsLiveData
    }

    fun add(product: Product): Boolean{
        return if (products.contains(product)){
            update(product)
        } else {
            products.add(product).also { getProductsLiveData() }
        }
    }

    fun update(product: Product): Boolean{
        val index = products.indexOf(product)
        if (index != -1){
            return products.set(index, product) != product.also { getProductsLiveData() }
        }
        return false
    }

    fun delete(product: Product): Boolean{
        val index = products.indexOf(product)
        if (index != -1){
            if (products.removeAt(index) == product){
                getProductsLiveData()
                return true
            }
        }
        return false
    }
}

private val names = listOf("Vino", "Queso", "Globo", "Sombrero", "Juguete", "Mango", "Chocolate",
    "Vela", "SSD", "Shampoo", "TV", "Tazón", "Nevera", "Pepinillos",
    "Hojas", "Bombilla", "Papas", "Jamón", "Tijeras", "Bolígrafo", "Soda")

private val urls = listOf(
    "https://cdn.pixabay.com/photo/2016/10/22/20/34/wines-1761613_960_720.jpg",
    "https://cdn.pixabay.com/photo/2010/12/13/10/24/cheese-2785_960_720.jpg",
    "https://cdn.pixabay.com/photo/2016/10/31/19/04/balloons-1786430_960_720.jpg",
    "https://cdn.pixabay.com/photo/2017/05/13/12/40/fashion-2309519_960_720.jpg",
    "https://cdn.pixabay.com/photo/2016/11/28/10/48/child-1864718_960_720.jpg",
    "https://cdn.pixabay.com/photo/2017/05/31/14/31/mango-2360551_960_720.jpg",
    "https://cdn.pixabay.com/photo/2017/04/12/16/56/chocolates-2224998_960_720.jpg",

    "https://cdn.pixabay.com/photo/2020/07/08/01/27/spa-5382251_960_720.jpg",
    "https://cdn.pixabay.com/photo/2014/04/09/08/16/data-storage-319844_960_720.jpg",
    "https://cdn.pixabay.com/photo/2016/11/26/12/52/bottle-1860617_960_720.png",
    "https://cdn.pixabay.com/photo/2016/11/21/12/10/tv-1844964_960_720.jpg",
    "https://cdn.pixabay.com/photo/2014/10/02/02/13/bowl-469295_960_720.jpg",
    "https://cdn.pixabay.com/photo/2016/10/24/21/03/appliance-1767311_960_720.jpg",
    "https://cdn.pixabay.com/photo/2016/07/15/22/49/pickled-cucumbers-1520638_960_720.jpg",

    "https://cdn.pixabay.com/photo/2017/10/14/09/56/journal-2850091_960_720.jpg",
    "https://cdn.pixabay.com/photo/2019/06/25/04/40/light-4297386_960_720.jpg",
    "https://cdn.pixabay.com/photo/2016/08/11/08/43/potatoes-1585060_960_720.jpg",
    "https://cdn.pixabay.com/photo/2017/05/02/14/55/black-forest-ham-2278383_960_720.jpg",
    "https://cdn.pixabay.com/photo/2014/04/10/19/53/scissors-321238_960_720.jpg",
    "https://cdn.pixabay.com/photo/2018/03/03/20/02/laptop-3196481_960_720.jpg",
    "https://cdn.pixabay.com/photo/2015/08/25/16/12/lime-907124_960_720.jpg")
