package com.cursosant.inventorybase.entities


data class Product(var id: Long = 0,
                   var name: String,
                   var quantity: Int,
                   var photoUrl: String = "",
                   var score: Double = 0.0,
                   var totalVotes: Long = 0)
