package com.pms.drugzx.datamodels.api

data class CustomerOrder(
    val customerAddress: String,
    val customerEmail: String,
    val customerName: String,
    val customerPhone: String,
    var orderProducts: List<OrderProduct>?,
    val sellerId: Int,
    val soDate: String,
    val soStatus: String
)