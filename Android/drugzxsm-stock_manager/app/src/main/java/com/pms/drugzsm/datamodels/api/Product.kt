package com.pms.drugzsm.datamodels.api

data class Product(
    val pCostPrice: Double,
    val pDescription: String,
    val pExpiryDate: String,
    val pManufactureDate: String,
    val pName: String,
    val pQuantity: Int,
    val pSellingPrice: Double,
    val sId: Int
)