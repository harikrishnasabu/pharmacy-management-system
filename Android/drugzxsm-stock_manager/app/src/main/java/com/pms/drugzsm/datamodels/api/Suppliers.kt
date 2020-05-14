package com.pms.drugzsm.datamodels.api

data class Suppliers(
    val address: String,
    val contactNo: Long,
    val email: String,
    val license: String,
    val supplierName: String,
    val supplierId:Int
)