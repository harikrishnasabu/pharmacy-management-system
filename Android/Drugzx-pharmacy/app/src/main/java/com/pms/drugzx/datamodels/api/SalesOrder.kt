package com.pms.drugzx.datamodels.api

data class SalesOrder(
    val customerAddress: String,
    val customerEmail: String,
    val customerName: String,
    val customerPhone: String,
    val orderProducts: List<OrderProductX>,
    val sellerId: Int,
    val soDate: String,
    val soId: Int,
    val soStatus: String,
    val subTotal: Double,
    val tax: Double,
    val total: Double,
    val totalQuantity: Int
)