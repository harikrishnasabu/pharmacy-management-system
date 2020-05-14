package com.pms.drugzsm.datamodels.api

data class OrdersList(
    val date: String,
    val orderProduct: List<OrderProduct>,
    val poId: String,
    val status: String,
    val subTotal: Double,
    val supplierId: Int,
    val tax: Double,
    val total: Double,
    val totalQuantity: Int
)