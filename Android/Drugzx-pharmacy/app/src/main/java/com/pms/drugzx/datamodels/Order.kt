package com.pms.drugzx.datamodels

data class Order(val customerName: String,
                 val product_count: Int,
                 val product_total: String,
                 val tax_total: String,
                 val grand_total: String) {


}
