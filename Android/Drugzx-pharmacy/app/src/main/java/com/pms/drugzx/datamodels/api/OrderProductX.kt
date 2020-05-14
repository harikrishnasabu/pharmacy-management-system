package com.pms.drugzx.datamodels.api

data class OrderProductX(
    val orderId: String,
    val price: Double,
    val productId: Int,
    val quantity: Int
)