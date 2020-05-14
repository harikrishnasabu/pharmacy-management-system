package com.pms.drugzx.ui.main

import androidx.lifecycle.ViewModel
import com.pms.drugzx.datamodels.api.CustomerOrder
import com.pms.drugzx.datamodels.api.OrderProduct
import com.pms.drugzx.repo.MainRepository

class CustomerOrderVM :ViewModel() {
    fun placeOrder(customerOrder: CustomerOrder){
        val orderProducts: ArrayList<OrderProduct> = ArrayList()
        MainRepository._selectedProducts.forEach {
            orderProducts.add(OrderProduct(it.pId,it.pQuantity))
        }
customerOrder.orderProducts=orderProducts
        MainRepository.sendOrder(customerOrder)
        println("Customer Order"+customerOrder.toString())
    }
}