package com.pms.drugzx.datamodels.api

data class Products(
    val pCostPrice: Double,
    val pDescription: String,
    val pExpiryDate: String,
    val pId: Int,
    val pManufactureDate: String,
    val pName: String,
    var pQuantity: Int,
    val pSellingPrice: Double,
    val sId: Int
)