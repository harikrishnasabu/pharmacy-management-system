package com.pms.drugzsm.datamodels.api

data class SupplierOrder(
    val date: String,
    val orderProduct: List<OrderProduct>,
    val status: String,
    val supplierId: Int
)