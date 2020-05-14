package com.pms.drugzsm.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pms.drugzsm.datamodels.api.OrderProduct
import com.pms.drugzsm.datamodels.api.Products
import com.pms.drugzsm.datamodels.api.SupplierOrder
import com.pms.drugzsm.datamodels.api.Suppliers
import com.pms.drugzsm.repo.MainRepository
import com.pms.drugzsm.utils.DateUtils
import com.pms.drugzsm.utils.InjectorUtils

class ProductListingVM : ViewModel() {
     var _products: LiveData<List<Products>> = MutableLiveData()
    private val _selectedProducts: MutableLiveData<List<Products>> = MutableLiveData()
    var _searchProduct:LiveData<List<Products>> = MutableLiveData()


    fun setProducts(selectedProducts: List<Products>) {
        _selectedProducts.value=selectedProducts
MainRepository._selectedProducts=selectedProducts
        println("_selectedProducts"+selectedProducts)
    }

    fun getProducts() {
        _products=MainRepository.getProducts()
 }
    fun getSelectedProducts(): List<Products>? {
        return  MainRepository._selectedProducts
    }

    fun searchchProduct(search: String) {

        _searchProduct= MainRepository.searchProducts(search)
    }

    fun placeOrder(){
        val orderProducts: ArrayList<OrderProduct> = ArrayList()
        MainRepository._selectedProducts.forEach {
            orderProducts.add(OrderProduct(it.pId,it.pQuantity))
        }
val supplierOrder = SupplierOrder(DateUtils.convertLongToStringDate(System.currentTimeMillis()),orderProducts,"Paid",MainRepository._selectedSupplier.supplierId)
        MainRepository.sendOrder(supplierOrder)
        println("supplierOrder "+supplierOrder.toString())
    }
}