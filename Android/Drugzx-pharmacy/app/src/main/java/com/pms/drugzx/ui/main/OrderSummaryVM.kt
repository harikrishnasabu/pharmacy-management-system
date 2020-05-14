package com.pms.drugzx.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.pms.drugzx.datamodels.Login
import com.pms.drugzx.datamodels.Order
import com.pms.drugzx.repo.MainRepository

class OrderSummaryVM: ViewModel()  {
    private val _summary: MutableLiveData<Order> = MutableLiveData()

fun getOrderSummary()=MainRepository._orderSummary



}