package com.pms.drugzsm.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pms.drugzsm.datamodels.Order
import com.pms.drugzsm.repo.MainRepository

class OrderSummaryVM: ViewModel()  {
    private val _summary: MutableLiveData<Order> = MutableLiveData()
  //  fun getOrderDetails()=MainRepository._customerOrder





}